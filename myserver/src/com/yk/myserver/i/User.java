package com.yk.myserver.i;

import java.util.ArrayList;

public interface User {
	public boolean Login( int id );
	public boolean Register( int id , String name , String tel , String passwd );
	public boolean Unregister( int id );
	public ArrayList<User> list();
	
	public String getName();
	public int getID();
	public String getTel();
}
