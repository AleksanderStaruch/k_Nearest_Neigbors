
public class Main {

    public static void main(String[] args) {

        IrisData irisData= new IrisData("iris.data.normalized.txt");
        //irisData.showDataTrening();

        irisData.checkDataTestAccuracy(10,"iris.data.normalized.test.txt");





    }
}
