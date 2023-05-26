package tw.niq.example.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateAccountModel {

	@NotNull(message = "User ID cannot be null")
	private String userId;
	
	@NotNull(message = "Address cannot be null")
	private String address;
	
	@NotNull(message = "City cannot be null")
	private String city;
	
	@NotNull(message = "State cannot be null")
	private String state;
	
	@NotNull(message = "Country cannot be null")
	private String country;
	
	@NotNull(message = "ZIP code cannot be null")
	private String zipCode;
	
	private String phoneNumber;
	
}
