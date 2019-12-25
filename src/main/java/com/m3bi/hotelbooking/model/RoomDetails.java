package com.m3bi.hotelbooking.model;

import com.m3bi.hotelbooking.entity.Room;

public class RoomDetails extends Room{
    private long bonusAmountApplied;
    private long updatedPrice;
	public RoomDetails(Room room, long bonusAmountApplied) {
		super(room);
		this.bonusAmountApplied = bonusAmountApplied;
		if(bonusAmountApplied < room.getPrice()) {
			this.updatedPrice = room.getPrice() - bonusAmountApplied;
		}
		else {
			this.updatedPrice = 0;
		}
	}
	
	
	public long getBonusAmountApplied() {
		return bonusAmountApplied;
	}


	public void setBonusAmountApplied(long bonusAmountApplied) {
		this.bonusAmountApplied = bonusAmountApplied;
	}


	public long getUpdatedPrice() {
		return updatedPrice;
	}


	public void setUpdatedPrice(long updatedPrice) {
		this.updatedPrice = updatedPrice;
	}


	@Override
	public String toString() {
		return super.toString()+" RoomDetails [bonusAmountApplied=" + bonusAmountApplied + ", updatedPrice=" + updatedPrice + "]";
	}

    
}
