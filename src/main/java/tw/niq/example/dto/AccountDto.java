package tw.niq.example.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class AccountDto implements Serializable {

	private static final long serialVersionUID = -3316263271231612892L;

	private String userId;
	
	private String address;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private String zipCode;
	
}
