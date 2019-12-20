package Atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * If the value inside the atomicReference is the first parameters (old name) the value in the atomic Reference
 * will be the newValue (new Name). Otherwise nothing happens. (the value inside atomic reference is oldName).
 * This happens in an atomic way.
 */
public class AtomicReferenceExample {

    public static void main (String[] args){
        String oldName = "old name";
        String newName = "new name";

        AtomicReference<String> atomicReference = new AtomicReference<String>(oldName);


        if(atomicReference.compareAndSet(oldName,newName)){
            System.out.println("New value is  : " + atomicReference.get());
        }else{
            System.out.println("Nothing happens");
        }

    }
}
