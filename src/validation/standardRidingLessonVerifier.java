package validation;

import java.util.Calendar;
import ridingTimeTable.interfaces.lessonChangeObserver;
import ridingTimeTable.interfaces.myDate;
import ridingTimeTable.interfaces.ridingLesson;

/**
 * The object is to verify that any given object matches a set of
 * rules:
 *   ensure that start and end are on one day      AND
 *   ensure that start and end are not the same    AND
 *   ensure that duration is the difference between beginn and ende AND
 *   ensure that duration is neither negativ nor zero AND
 *   ensure that end can not be earlier than start (this is already ensured
 *   by the preceeding rules)
 * 
 * No exceptions are thrown. Therefor a message is generated in case an 
 * inconsistency would rise.
 * 
 * @author Joe
 */
public final class standardRidingLessonVerifier implements lessonChangeObserver {

    myDate begin;
    myDate end;
    int place;
    int teacher;
    long duration;
    private ridingLesson rL;
    private static standardRidingLessonVerifier V;

    private standardRidingLessonVerifier() {
    }

    public static synchronized standardRidingLessonVerifier getInstance() {
        if (V == null) {
            V = new standardRidingLessonVerifier();
        }
        return V;
    }

    private void isValid(ridingLesson lesson) {
        rL = lesson;
        begin = lesson.getStart();
        end = lesson.getEnd();
        place = lesson.getPlace();
        teacher = lesson.getTeacher();

        isStartAndEndOnOneDay();
        isStartAndEndNotSame();
        isDurationPositiv();
    }

    /**
     * Check that start and end are on one day
     * @return true if it is so
     */
    private void isStartAndEndOnOneDay() {
        if (!(begin.getMyDate().get(Calendar.DAY_OF_YEAR) == end.getMyDate().get(Calendar.DAY_OF_YEAR))
                && (begin.getMyDate().get(Calendar.YEAR) == end.getMyDate().get(Calendar.YEAR))) {
            System.out.println("Start and End on different days!" + rL);
        }
    }

    /**
     * May be start and end are the same value
     * @return true if they are not
     */
    private void isStartAndEndNotSame() {
        if (begin.getMyDate().equals(end.getMyDate())) {
            System.out.println("Start and End are the same!" + rL);
        }
    }

    /**
     * Duration must not be negativ or zero
     * @return 
     */
    private void isDurationPositiv() {
        if (end.getMyDate().before(begin.getMyDate())) {
            System.out.println("End before start!" + rL);
        }
    }

    @Override
    public void notifyChange(ridingLesson lesson) {
        isValid(lesson);
    }
}
