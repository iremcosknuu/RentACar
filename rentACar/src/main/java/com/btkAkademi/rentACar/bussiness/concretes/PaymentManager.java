package com.btkAkademi.rentACar.bussiness.concretes;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.bussiness.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.bussiness.abstracts.PaymentService;
import com.btkAkademi.rentACar.bussiness.abstracts.RentalService;
import com.btkAkademi.rentACar.bussiness.constants.Messages;
import com.btkAkademi.rentACar.bussiness.dtos.PaymentListDto;
import com.btkAkademi.rentACar.bussiness.requests.paymentRequests.CreatePaymentRequest;
import com.btkAkademi.rentACar.bussiness.requests.paymentRequests.UpdatePaymentRequest;
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
import com.btkAkademi.rentACar.entities.concretes.Rental;

@Service
public class PaymentManager implements PaymentService{

	private PaymentDao paymentDao;
    private ModelMapperService modelMapperService;
    private RentalService rentalService;
    private AdditionalServiceService additionalServiceService;
    private BankAdapterService bankAdapterService;

	@Autowired
	public PaymentManager(ModelMapperService modelMapperService
			, PaymentDao paymentDao
			, RentalService rentalService
			, AdditionalServiceService additionalServiceService
			, BankAdapterService bankAdapterService) {
		super();
		this.modelMapperService = modelMapperService;
		this.paymentDao = paymentDao;
		this.rentalService = rentalService;
		this.additionalServiceService=additionalServiceService;
		this.bankAdapterService=bankAdapterService;
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
        
        Rental rental = rentalService.findRentalById(rentalId).getData();
        
		double totalPrice = calculatorTotalPrice(rental);
		
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
	
	private double calculatorTotalPrice(Rental rental) {
		double totalPrice= 0.0;
		long days= ChronoUnit.DAYS.between(rental.getReturnDate(), rental.getRentDate());
		if(days==0) {
			days=1;
		}
		
		totalPrice+=days*rental.getCar().getDailyPrice();
		
		for(AdditionalService additionalService:rental.getAddtionalServices()) {
			
			totalPrice+=additionalService.getPrice();
			
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
