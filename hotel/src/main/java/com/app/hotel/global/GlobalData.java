package com.app.hotel.global;

import java.util.ArrayList;
import java.util.List;

import com.app.hotel.model.Contents;

public class GlobalData {
	
	public static List<Contents> cart;
	static {
		cart=new ArrayList<Contents>();
	}

}
