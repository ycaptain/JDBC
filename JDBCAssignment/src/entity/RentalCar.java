package entity;

import java.util.Date;

public class RentalCar extends Car{

	public RentalCar(){}
	
	public RentalCar(int registrationNumber, String model, Status status, Date dateOfManuFacture) {
		super(registrationNumber, model, status, dateOfManuFacture);
	}
	
}