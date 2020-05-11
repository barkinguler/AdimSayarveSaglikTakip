package com.example.yurumesayar.Models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bilgiler{
	private String SIFRE;
	private int ADIM;
	private String KULLANICI;
	private int TOPLAM;
	private String TARIH;
	private float KILO;
	private float ORT;
	private int MAX;
	private String HATA;

	public String getHATA() {
		return HATA;
	}

	public void setHATA(String HATA) {
		this.HATA = HATA;
	}

	public float getORT() {
		return ORT;
	}

	public void setORT(float ORT) {
		this.ORT = ORT;
	}

	public int getMAX() {
		return MAX;
	}

	public void setMAX(int MAX) {
		this.MAX = MAX;
	}

	public float getKILO() {
		return KILO;
	}

	public void setKILO(float KILO) {
		this.KILO = KILO;
	}

	public String getTARIH() {
		return TARIH;
	}

	public void setTARIH(String TARIH) {
		this.TARIH = TARIH;
	}

	public int getTOPLAM() {
		return TOPLAM;
	}

	public void setTOPLAM(int TOPLAM) {
		this.TOPLAM = TOPLAM;
	}

	public void setSIFRE(String SIFRE){
		this.SIFRE = SIFRE;
	}

	public String getSIFRE(){
		return SIFRE;
	}

	public void setADIM(int ADIM){
		this.ADIM = ADIM;
	}

	public int getADIM(){
		return ADIM;
	}

	public void setKULLANICI(String KULLANICI){
		this.KULLANICI = KULLANICI;
	}

	public String getKULLANICI(){
		return KULLANICI;
	}

	@Override
 	public String toString(){
		return 
			"Bilgiler{" +
			"SIFRE = '" + SIFRE + '\'' +
			",ADIM = '" + ADIM + '\'' +
			",KULLANICI = '" + KULLANICI + '\'' +
			"}";
		}
	public String toString1(){
		return
				"{" +
						"\"SIFRE\":"+"\""	 + SIFRE +"\""+
						","+"\"ADIM\":"+"\"" + ADIM + "\"" +
						","+"\"KULLANICI\":"+"\"" + KULLANICI + "\"" +
						"}";
	}
	public String toStringUpdate(){
		return
				"{" +
						"\"KULLANICI\":"+"\"" + KULLANICI + "\"" +

						"}";
	}
	public String adımgüncelle(){

		return  "{" +
				"\"TARIH\":"+"\"" + ADIM + "\"" +
				","+"\"KULLANICI\":"+"\"" + KULLANICI + "\"" +
				"}";

	}

	public String toKıtle(){

		return  "{" +
				"\"KILO\":"+"\"" + KILO + "\"" +
				","+"\"KULLANICI\":"+"\"" + KULLANICI + "\"" +
				"}";

	}

}
