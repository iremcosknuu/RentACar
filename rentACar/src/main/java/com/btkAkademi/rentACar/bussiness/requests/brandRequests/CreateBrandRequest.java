package com.btkAkademi.rentACar.bussiness.requests.brandRequests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.btkAkademi.rentACar.bussiness.constants.Messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBrandRequest {
	@NotBlank
	@Size(min=3, max=20,message = Messages.invalidBrandName)
	private String name;
}
