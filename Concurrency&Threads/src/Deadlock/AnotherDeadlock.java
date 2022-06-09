package Deadlock;

class AnotherDeadlock{

    public static void main(String[] arg)
    {
        final PolitePerson aashraya = new PolitePerson("Aashraya");
        final PolitePerson aditya = new PolitePerson("Adity");

        // This code will work without a deadlock as it'll run on a single thread hence the second statement will run only after the first has returned!
        {
            // aashraya.sayHello(aditya);
            // aditya.sayHello(aashraya);
        }

        // This code will cause a deadlock as the two statements wait for the other to finish so that they can acquire the lock to call the sayHelloBack method XD
        {
            new Thread(new Runnable() {
                public void run(){
                    aashraya.sayHello(aditya);
                }
            }).start();

            new Thread(new Runnable() {
                public void run(){
                    aditya.sayHello(aashraya);
                }
            }).start();

            try {
                Thread.sleep(10000);
                System.out.println("\n" + "Still stuck eh? Ctrl + C ;p");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    static class PolitePerson{
        String name;

        public PolitePerson(String name) {
            this.name = name;
        }

        public String getName(){
            return this.name;
        }

        public synchronized void sayHello(PolitePerson person){
            System.out.format("%s : %s said hello to me! :D %n", this.name, person.getName());
            person.sayHelloBack(this);
        }


        public synchronized void sayHelloBack(PolitePerson person){
            System.out.format("%s : %s has said hello back to me! ;p %n", this.name, person.getName());
        }
    }
    
}