package PoliteWorker;

public class LiveLocks {

    // The LazyWorker will pass the sharedResource to the other worker if they seem them active.
    // If they aren't active tho, then they'll actually work and pass it on after that :)
    //
    // Since both workers are active, so the resource keeps getting passed and none actually work XD
    // #LiveLock you see XD


    public static void main(String[] args) {
        final LazyWorker worker1 = new LazyWorker("Jack", true);
        final LazyWorker worker2 = new LazyWorker("Jill", true);
    
        final SharedResource sharedResource = new SharedResource(worker1);
    
        new Thread(new Runnable() {
            public void run(){
                worker1.work(sharedResource, worker2);
            }
        }).start();;
    
        new Thread(new Runnable() {
            public void run(){
                worker2.work(sharedResource, worker1);
            }
        }).start();;
    

    }
    
}

class LazyWorker{

    private String name;
    private boolean isActive;

    public LazyWorker(String name, boolean isActive){
        this.name = name; 
        this.isActive = isActive;
    }

    public String getName(){return this.name;}

    public boolean isActive(){return this.isActive;}

    // Thread will try to get the sharedresource, 
    // Once it gets the resource, it check on otherWorker, if it's active, it'll politely give it to the otherWorker.
    //

    public synchronized void work(SharedResource sharedResource, LazyWorker otherWorker)
    {
        while(isActive){
            if(sharedResource.getOwner() != this){
                try{
                    wait(10);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
                continue;
            }

            if(otherWorker.isActive()){
                System.out.println(this.getName() + " : Giving the resource to " + otherWorker.getName());
                sharedResource.setOwner(otherWorker);
                continue;
            }
            
            System.out.println(getName() + " : Working on SharedResource");
            try{   
                // Working XD
                Thread.sleep(1000);
            } catch(InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println(getName() + " : Worked on SharedResource");
            isActive = false;
            sharedResource.setOwner(otherWorker);
        
        }
    }
} 
 

class SharedResource {

    private LazyWorker owner;

    public SharedResource(LazyWorker owner){
        this.owner = owner;
    }

    public LazyWorker getOwner(){return this.owner;}

    public synchronized void setOwner(LazyWorker owner){this.owner = owner;}

}