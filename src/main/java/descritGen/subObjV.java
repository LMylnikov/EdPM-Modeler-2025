package descritGen;

import java.util.ArrayList;

public class subObjV {
    private String linkedNv = "";
    private String linkedO = "";
    private ArrayList<String> arrayLinkedSR = new ArrayList<String>();
    private String oValue;
    private String nameV;
    private String type;
    
    public subObjV(String name,String type){ //стандартный генератор с прикреплением имени
        nameV = name;
        setNumberType(type);
    }
    //генератор для клонирования объекта
    public subObjV(String name,String type,  ArrayList<String> oldSR,  String oldNv, String oldO, String oldOValue){ //стандартный генератор с прикреплением имени
        nameV = name;
        this.type = type;
        linkedO = oldO;
        for (String el: oldSR){
            arrayLinkedSR.add(el);
        }
        linkedNv = oldNv;
        oValue = oldOValue;
    }

    public void AddToSRList(String vName){
        arrayLinkedSR.add(vName);
    }
    public boolean isEmpty(){
        if (linkedNv == "" & linkedO == "" & arrayLinkedSR.size() == 0){
            return true;
        }
        return false;
    }
    public String getLinkedNv(){
        return linkedNv;
    }
    public void setLinkedNv(String link){
        linkedNv = link;
    }
    public String getLinkedO(){
        return linkedO;
    }
    public void setLinkedO(String link){
        linkedO = link;
    }
    public ArrayList<String> getArrayLinkedSR(){
        return arrayLinkedSR;
    }
    public void setArrayLinkedSR(ArrayList<String> array){
        arrayLinkedSR = array;
    }
    public String getType(){
        return type;
    }
    public void setType(String type){
        setNumberType(type);
    }
    public String getName(){
        return nameV;
    }
    public void setName(String name){
        nameV = name;
    }
    public void setOValue(String number){
        oValue=number;
    }
    public String getOValue(){
        return oValue;
    }
    private void setNumberType(String textType){
        switch (textType.charAt(2)){
            case('с'): //эксп
                type = "4";
                break; 
            case('е'): //Элем
                type = "1";
                break;
            case('ш'): //1 шаг
                type = "2";
                break; 
            case('г'): //Логар
                type = "3";
                break;
            case('o'): //xlog
                type = "5";
                break;
            case('д'): //Индивид функция
                type = "6";
                break;
        }
    }
}
