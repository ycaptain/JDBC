package dao;

import entity.SoldCar;

public interface SoldCarDao {
	public void addSoldCar(SoldCar soldCar);
	public SoldCar getSoldCar(int SNumber);
	public int update(SoldCar soldCar);
	public int delete(SoldCar soldCar);
}
