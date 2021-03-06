package com.pedidosclientes.Cliente;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cliente 
{
	private String nombreIn;
	private float precioIn;
	private int mesIn;
	private int diaIn; 
	private int horaIn;
	private int minutoIn;
	
	public String getNombreIn() {
		return nombreIn;
	}

	public void setNombreIn(String nombreIn) {
		this.nombreIn = nombreIn;
	}

	public float getPrecioIn() {
		return precioIn;
	}

	public void setPrecioIn(float precioIn) {
		this.precioIn = precioIn;
	}

	public int getMesIn() {
		return mesIn;
	}

	public void setMesIn(int mesIn) {
		this.mesIn = mesIn;
	}

	public int getDiaIn() {
		return diaIn;
	}

	public void setDiaIn(int diaIn) {
		this.diaIn = diaIn;
	}

	public int getHoraIn() {
		return horaIn;
	}

	public void setHoraIn(int horaIn) {
		this.horaIn = horaIn;
	}

	public int getMinutoIn() {
		return minutoIn;
	}

	public void setMinutoIn(int minutoIn) {
		this.minutoIn = minutoIn;
	}

	String nombreF;
	float precioF;
	int mesF, diaF, horaF, minutoF;
	
	public int tDevolucion;
	public float vPenalidad; 
	public String mensaje;
	
	private static String[][] nuevoCliente = new String[100][6];
			
	//Peticion POST
	public String[][] peticionCliente()
	{			
		for(int i=0; i<=(nuevoCliente.length -1); i++)
		{
			if(nuevoCliente[i][0] == null)
			{
				for(int n=0; n<=5; n++)
				{
					if(n==0)
					{
						nuevoCliente[i][n] = nombreIn;
					}
					else if(n==1)
					{
						nuevoCliente[i][n] = Float.toString(precioIn);
					}
					else if(n==2)
					{
						nuevoCliente[i][n] = Integer.toString(mesIn);
					}
					else if(n==3)
					{
						nuevoCliente[i][n] = Integer.toString(diaIn);
					}
					else if(n==4)
					{
						nuevoCliente[i][n] = Integer.toString(horaIn);
					}
					else if(n==5)
					{
						nuevoCliente[i][n] = Integer.toString(minutoIn);
					}
				}
				break;
			}
		}
		
		return nuevoCliente;
	}
 
	// GET //
	public String[][] getAll()
	{		
		return nuevoCliente;
	}
	
	// DELETE //
	public String removerCliente(String nombre)
	{						

		for(int i=0; i<=(nuevoCliente.length -1); i++)
		{			
			if(nuevoCliente[i][0] != null)
			{
				if(nuevoCliente[i][0].equals(nombre))
				{
					mensaje = tiempo(nuevoCliente[i][1], nuevoCliente[i][3], nuevoCliente[i][2], nuevoCliente[i][4], nuevoCliente[i][5]);
					
					for(int n=0; n<=5; n++)
					{
						nuevoCliente[i][n] = null;
					}
					
					break;
				}
				else
				{
					mensaje = "El nombre de usuario no se encuentra";
				}
			}
		}
					
		return "El cliente " + nombre + mensaje;
	}
	
	// Ac?? se analiza si la compra ya cumplio o no las 12h. //
	public String tiempo(String price, String day, String month, String hour, String minute)
	{
		LocalTime hourCancellationBuy = LocalTime.now();
		LocalDate currentDay = LocalDate.now();
		int horaActual = hourCancellationBuy.getHour(); 
		int minutoActual = hourCancellationBuy.getMinute();
		int actualDay = currentDay.getDayOfMonth();
		int actualMonth = currentDay.getMonthValue();	
		
		int dayBuy = Integer.parseInt(day);
		int hourBuy = Integer.parseInt(hour);
		int minuteBuy = Integer.parseInt(minute);
		int monthBuy = Integer.parseInt(month);
		float priceBuy = Float.parseFloat(price);
		
		int conversionMin;
		
		if(hourBuy > horaActual)
		{
			conversionMin = (24 + horaActual - hourBuy) * 60;
			conversionMin = conversionMin - minuteBuy + minutoActual;
		}
		else
		{
			conversionMin = (horaActual - hourBuy) * 60;
			conversionMin = conversionMin - minuteBuy + minutoActual;
		}
		
		//Conditions
		if(dayBuy <= actualDay && monthBuy == actualMonth)
		{	
			if(conversionMin <= 720)
			{
				vPenalidad = 0; 
				mensaje =" el d??a " + dayBuy + " a las " + hourBuy + ":" + minuteBuy + " " + " hizo una compra con un total de $" 
				+ priceBuy + ".\n" + "La devoluci??n no tiene penalidad\nTotal a pagar $" + vPenalidad +"";
			}
			else 
			{
				vPenalidad = (priceBuy * 10)/100;
				mensaje =" el d??a " + dayBuy + " a las " + hourBuy + ":" + minuteBuy + " " + " hizo una compra con un total de $" + 
							priceBuy + ".\n" + "La devoluci??n presenta una penalidad de $" + vPenalidad +" a pagar.";
			}
		}
		else
		{
			mensaje = "Cancelaci??n no aprobada";
		}
		
		return mensaje; 
	}
	
}
