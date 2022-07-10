/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package idgenerator32bit;

import java.util.LinkedList;

/**
 *
 * @author noname
 */
public class IdGenerator32Bit {
    LinkedList<UnusedId> objUnusedIdList = new LinkedList<>(); 
    
    class UnusedId{
        int iStart;
        int iSize;
        
        //**Constructor
        public UnusedId(int _iStart , int _iSize){
            iStart = _iStart;
            iSize = _iSize;
        }
    }
    
    //**Constructor
    public IdGenerator32Bit(){
        //**Initialize
        UnusedId objHead = new UnusedId(0 , Integer.MAX_VALUE);
        objUnusedIdList.add(objHead);
    }
    
    public int getId(){
        if(!objUnusedIdList.isEmpty()){
            UnusedId objTmp = objUnusedIdList.peekFirst();
            //**Checking if the first node is empty or not
            if(--objTmp.iSize == 0)
                objUnusedIdList.remove(objTmp);
            return objTmp.iStart++;
        }
        return -1;
    }
    
    public void freeId(int _iId){
        if(_iId < 0){
            System.out.println("Unvalid");
            return;
        }
        else if(objUnusedIdList.isEmpty()){
            objUnusedIdList.add(new UnusedId(_iId , 1));
            System.out.println("Empty");
            return;
        }
        int iIndex = 0;
        for(UnusedId E : objUnusedIdList){
            if(_iId < E.iStart){
                objUnusedIdList.add(iIndex , new UnusedId(_iId , 1));
                break;
            }
            iIndex++;
        }
        if(iIndex == objUnusedIdList.size())
            objUnusedIdList.addLast(new UnusedId(_iId , 1));
          
        UnusedId objPre = null;
        iIndex = 0;
        for(UnusedId E : (LinkedList<UnusedId>)objUnusedIdList.clone()){
            if(objPre != null && canMerge(objPre,E)){
                objUnusedIdList.remove(iIndex);
                objUnusedIdList.remove(iIndex-1);
                objPre = new UnusedId(objPre.iStart,E.iSize+objPre.iSize);
                objUnusedIdList.add(iIndex-1,objPre);
            }
            else{
                objPre = E;
                iIndex++;
            }
        }
        System.out.println("\nNode Information Free : " + _iId);
        iIndex = 0;
        for(UnusedId E : objUnusedIdList)
            System.out.println(++iIndex + ". Start : " + E.iStart + " Size : " + E.iSize);
    }
    
    private boolean canMerge(UnusedId _objFirst , UnusedId _objSecond){
        if(_objFirst == null || _objSecond == null)
            return false;
        else if(_objFirst.iStart+_objFirst.iSize == _objSecond.iStart)
            return true;
        return false;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        IdGenerator32Bit objIdGenerator = new IdGenerator32Bit();
        for(int iCounter = 0 ; iCounter < 11 ; iCounter++)
            System.out.println(objIdGenerator.getId());
        objIdGenerator.freeId(0);
        objIdGenerator.freeId(7);
        objIdGenerator.freeId(9);
        objIdGenerator.freeId(8);
        objIdGenerator.freeId(4);
        objIdGenerator.freeId(1);
        objIdGenerator.freeId(3);
        objIdGenerator.freeId(2);
        objIdGenerator.freeId(5);
        objIdGenerator.freeId(6);
        objIdGenerator.freeId(10);
        System.out.println(objIdGenerator.getId());
        System.out.println(objIdGenerator.getId());
        System.out.println(objIdGenerator.getId());
        System.out.println(objIdGenerator.getId());
        System.out.println(objIdGenerator.getId());
        System.out.println(objIdGenerator.getId());
        System.out.println(objIdGenerator.getId());
    }
    
}
