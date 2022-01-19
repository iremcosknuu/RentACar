package com.btkAkademi.rentACar.business.concretes;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.abstracts.PaymentService;
import com.btkAkademi.rentACar.business.abstracts.PromotionService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.AdditionalServiceListDto;
import com.btkAkademi.rentACar.business.dtos.PaymentListDto;
import com.btkAkademi.rentACar.business.dtos.PromotionListDto;
import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btkAkademi.rentACar.business.requests.paymentRequests.UpdatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.adapters.bank.abstracts.BankAdapterService;
import com.btkAkademi.rentACar.core.utilities.business.businessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.PaymentDao;
import com.btkAkademi.rentACar.entities.concretes.AdditionalService;
import com.btkAkademi.rentACar.entities.concretes.Payment;
import com.btkAkademi.rentACar.entities.concretes.Promotion;
import com.btkAkademi.rentACar.entities.concretes.Rental;

@Service
public class PaymentManager implements PaymentService{

	private PaymentDao paymentDao;
    private ModelMapperService modelMapperService;
    private RentalService rentalService;
    private AdditionalServiceService additionalServiceService;
    private BankAdapterService bankAdapterService;
    private PromotionService promotionService;
    private CarService carService;

	@Autowired
	public PaymentManager(ModelMapperService modelMapperService
			, PaymentDao paymentDao
			, RentalService rentalService
			, AdditionalServiceService additionalServiceService
			, BankAdapterService bankAdapterService
			, PromotionService promotionService
			, CarService carService) {
		super();
		this.modelMapperService = modelMapperService;
		this.paymentDao = paymentDao;
		this.rentalService = rentalService;
		this.additionalServiceService=additionalServiceService;
		this.bankAdapterService=bankAdapterService;
		this.promotionService = promotionService;
		this.carService = carService;
	}

	@Override
	public DataResult<List<PaymentListDto>> getAll() {
		List<Payment> paymentList = this.paymentDao.findAll();
		List<PaymentListDto> response = paymentList.stream()
				.map(payment -> modelMapperService.forDto()
				.map(paymentList, PaymentListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<PaymentListDto>>(response);
	}

	@Override
	public Result add(CreatePaymentRequest createPaymentRequest) {
		
		Payment payment= modelMapperService.forRequest().map(createPaymentRequest, Payment.class); 
		
        int rentalId = createPaymentRequest.getRentalId();
        
        RentalListDto rentalList = rentalService.findById(rentalId).getData();
        
		double totalPrice = calculatorTotalPrice(rentalList);
		
		payment.setTotalPrice(totalPrice);
		
        Result result = businessRules.run(
        		bankAdapterService.checkIfLimitEnough(createPaymentRequest.getCardNo(), createPaymentRequest.getExpirationDate(), createPaymentRequest.getCVV(), totalPrice)
        );

        
        if(!result.isSuccess()) {
            return result;
        }

        this.paymentDao.save(payment);
        
        return new SuccessResult(Messages.paymentAdded);
	}

	@Override
	public Result update(UpdatePaymentRequest updatePaymentRequest) {
		Result result = businessRules.run(
				checkIfPaymentIdExists(updatePaymentRequest.getId())
				);
		if(result != null) {
			return result;
		}
		
		Payment paymentToUpdate = this.paymentDao.getById(updatePaymentRequest.getId());
		paymentToUpdate= modelMapperService.forRequest().map(updatePaymentRequest, Payment.class);
		this.paymentDao.save(paymentToUpdate);
		return new SuccessResult(Messages.paymentUpdated);
	}

	@Override
	public Result delete(int id) {
		Result result = businessRules.run(
				checkIfPaymentIdExists(id)
				);
		if(result != null) {
			return result;
		}
		
		this.paymentDao.deleteById(id);
		return null;
	}
	
	private double calculatorTotalPrice(RentalListDto rentalList) {
		double totalPrice= 0.0;
		long days= ChronoUnit.DAYS.between(rentalList.getReturnDate(), rentalList.getRentDate());
		if(days==0) {
			days=1;
		}
		
		totalPrice += days * carService.findById(rentalList.getCarId()).getData().getDailyPrice();
		
		if(rentalList.getPromotionId() != 0) {
			PromotionListDto promotionList = this.promotionService.findById(rentalList.getPromotionId()).getData();
			if(!promotionList.getPromotionEndDate().isAfter(rentalList.getReturnDate())){
				double discountRate = 0;
				discountRate = promotionList.getDiscountRate();
				totalPrice = totalPrice - (totalPrice*discountRate);
			}
			
		}
	    
		List<AdditionalServiceListDto> services = additionalServiceService.findAllByRentalId(rentalList.getId()).getData();
		for (AdditionalServiceListDto additionalService : services) {
			totalPrice += additionalService.getPrice();
		}
		
		return totalPrice;
	}
	
	public Result checkIfPaymentIdExists(int id) {
		if(!this.paymentDao.existsById(id)) {
			return new ErrorResult(Messages.paymentIdNotExists);
		}
		return new SuccessResult();
	}
}
