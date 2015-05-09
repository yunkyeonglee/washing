package com.yk.myserver.i;

public interface Reservation {
	public boolean setReservation( User user , Machine machine , String stime , String etime );
}
