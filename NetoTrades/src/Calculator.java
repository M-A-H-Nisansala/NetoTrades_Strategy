import java.sql.Date;
import java.util.ArrayList;


public class Calculator {

	public double FindAverage(ArrayList<Offer> offers){
		double sum = 0;
		double avg = 0;

		for (int i = 0; i < offers.size(); i++) {
			sum += offers.get(i).getOfferPrice();
		}

		avg = sum / offers.size();

		return avg;
	}
	
	public double GenerateFittedOffer(DetectionRegion detReg, int i, int j, ArrayList<Offer> offerHistory, Offer newOffer, int numberOfRounds){
		
		float tpSum = 0;
		float t2Sum = 1;
		
		for(int k = 0; k < numberOfRounds; k++){
			
			float tStar = (float) Math.log(newOffer.getOfferTime().getTime() / detReg.getCells()[i][j].getCellDeadline().getTime());
			float pStar = (float) Math.log((offerHistory.get(0).getOfferPrice() - newOffer.getOfferPrice()) / (offerHistory.get(0).getOfferPrice() - detReg.getCells()[i][j].getCellReservePrice()));
	
			tpSum += tStar * pStar;
			t2Sum += Math.pow(tStar, 2);
		}
		
		float b = tpSum / t2Sum;

		double offer = (double) (offerHistory.get(0).getOfferPrice() + (detReg.getCells()[i][j].getCellReservePrice() - offerHistory.get(0).getOfferPrice())* Math.pow((newOffer.getOfferTime().getTime() / detReg.getCells()[i][j].getCellDeadline().getTime()), b));
		return offer;
	}
	
	public double GenerateFittedOffer(DetectionRegion detReg, int i, int j, ArrayList<Offer> offerHistory, Offer newOffer, int numberOfRounds, Date time){
		float tpSum = 0;
		float t2Sum = 1;
		
		for(int k = 0; k < numberOfRounds; k++){
			
			float tStar = (float) Math.log(newOffer.getOfferTime().getTime() / detReg.getCells()[i][j].getCellDeadline().getTime());
			float pStar = (float) Math.log((offerHistory.get(0).getOfferPrice() - newOffer.getOfferPrice()) / (offerHistory.get(0).getOfferPrice() - detReg.getCells()[i][j].getCellReservePrice()));
	
			tpSum += tStar * pStar;
			t2Sum += Math.pow(tStar, 2);
		}
		
		float b = tpSum / t2Sum;
		
		double offer = (double)(offerHistory.get(0).getOfferPrice() + (detReg.getCells()[i][j].getCellReservePrice() - offerHistory.get(0).getOfferPrice())*Math.pow(time.getTime() / detReg.getCells()[i][j].getCellDeadline().getTime(), b));
		return offer;
	}
	
	public double generateGammaValue(Offer newOffer, double avgHistory, int numberOfRounds, double avgFitted, int i, int j, DetectionRegion detReg){

		double sumUP=0.0;
		double sumDown1=0.0;
		double sumDown2=0.0;
		
		for (int round = 1; round <= numberOfRounds; round++) {
			
			sumUP += (newOffer.getOfferPrice() - avgHistory)* (detReg.getCells()[i][j].getCellReservePrice() - avgFitted);
			sumDown1 += Math.pow((newOffer.getOfferPrice() - avgHistory), 2);
			sumDown2 += Math.pow(detReg.getCells()[i][j].getCellReservePrice() - avgFitted, 2); 			
		}
		
		double foundGamma= sumUP/Math.sqrt(sumDown1*sumDown2);
		return foundGamma;		
	}
}
