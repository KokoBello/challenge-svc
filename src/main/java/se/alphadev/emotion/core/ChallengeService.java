package se.alphadev.emotion.core;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Component;

import se.alphadev.emotion.api.Challenge;
import se.alphadev.emotion.api.FaceAndScores;
import se.alphadev.emotion.api.FaceRectangle;

@Component
public class ChallengeService {

	public FaceRectangle evaluate(Challenge challenge, List<FaceAndScores> faceAndScores) {
		
		switch (challenge) {
		case anger:
			Comparator<FaceAndScores> angerComparator = new Comparator<FaceAndScores>() {
				@Override public int compare(FaceAndScores o1, FaceAndScores o2) {
					return o1.scores.anger.compareTo( o2.scores.anger) ;
				}
			};
			return max(faceAndScores, angerComparator);
		case contempt:
			Comparator<FaceAndScores> contemptComparator = new Comparator<FaceAndScores>() {
				@Override public int compare(FaceAndScores o1, FaceAndScores o2) {
					return o1.scores.contempt.compareTo( o2.scores.contempt) ;
				}
			};
			return max(faceAndScores, contemptComparator);
		case disgust:
			Comparator<FaceAndScores> disgustComparator = new Comparator<FaceAndScores>() {
				@Override public int compare(FaceAndScores o1, FaceAndScores o2) {
					return o1.scores.disgust.compareTo( o2.scores.disgust) ;
				}
			};
			return max(faceAndScores, disgustComparator);
		case fear:
			Comparator<FaceAndScores> fearComparator = new Comparator<FaceAndScores>() {
				@Override public int compare(FaceAndScores o1, FaceAndScores o2) {
					return o1.scores.fear.compareTo( o2.scores.fear) ;
				}
			};
			return max(faceAndScores, fearComparator);
		case happiness:
			Comparator<FaceAndScores> happinessComparator = new Comparator<FaceAndScores>() {
				@Override public int compare(FaceAndScores o1, FaceAndScores o2) {
					return o1.scores.happiness.compareTo( o2.scores.happiness) ;
				}
			};
			return max(faceAndScores, happinessComparator);
		case neutral:
			Comparator<FaceAndScores> neutralComparator = new Comparator<FaceAndScores>() {
				@Override public int compare(FaceAndScores o1, FaceAndScores o2) {
					return o1.scores.neutral.compareTo( o2.scores.neutral) ;
				}
			};
			return max(faceAndScores, neutralComparator);
		case sadness:
			Comparator<FaceAndScores> sadnessComparator = new Comparator<FaceAndScores>() {
				@Override public int compare(FaceAndScores o1, FaceAndScores o2) {
					return o1.scores.sadness.compareTo( o2.scores.sadness) ;
				}
			};
			return max(faceAndScores, sadnessComparator);
		case surprise:
			Comparator<FaceAndScores> surpriseComparator = new Comparator<FaceAndScores>() {
				@Override public int compare(FaceAndScores o1, FaceAndScores o2) {
					return o1.scores.surprise.compareTo( o2.scores.surprise) ;
				}
			};
			return max(faceAndScores, surpriseComparator);

		}
		
		throw new ChallengeServiceException("Unknown challenge "+ challenge);
	}

	private FaceRectangle max(List<FaceAndScores> faceAndScores, Comparator<FaceAndScores> angerComparator) {
		return faceAndScores.stream().max(angerComparator).get().faceRectangle;
	}
}
