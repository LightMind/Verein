package validation.lesson;

import java.util.Calendar;

import plan.Lesson;
import validation.Validator;

public class LessonLengthValidator implements Validator<Lesson> {

	@Override
	public boolean isValid(Lesson object) {
		Calendar begin = object.getBeginn();
		Calendar end = object.getEnd();
		if ( begin.after(end)){
			// Lesson begins after it ended.
			return false;
		}
		if ( begin.get(Calendar.DAY_OF_YEAR) != end.get(Calendar.DAY_OF_YEAR) ){
			// different day
		    return false;
		}
		
		if ( begin.get(Calendar.YEAR) != end.get(Calendar.YEAR)){
			// different year
			return false;
		}
		
		return true;
	}

}
