package tests;

import com.epam.task1.exceptions.SphereParsingException;
import com.epam.task1.logics.parser.SphereParser;
import com.epam.task1.logics.validator.SphereValidator;
import com.epam.task1.logics.validator.Validator;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class ParserTest {
    private static final double DELTA = 0.001;
    private static  final SphereValidator MOCK = mock(SphereValidator.class);
    {
        when(MOCK.validate(any(String.class))).thenReturn(true);
    }
    private static final SphereParser parser = new SphereParser(MOCK);
    private static final double [] FIRST_TEST_DATA = new double[]{1.0, 2.7, 3.0, -4.0};
    private static final double [] SECOND_TEST_DATA = new double[]{5.2, 2.1, 2.0, 2.2};
    private static final double [] THIRD_TEST_DATA = new double[]{4.0, 6.1, 4.0, 4.0};
    private static final double [] FOURTH_TEST_DATA = new double[]{5.0, 6.0, 2.0, 4.0};

    @Test
    public void shouldReturnExpectedSphereDataWhenInputIsExpectedStringData() throws SphereParsingException{
        //given
        String expectedStringData = "1.0 2.7 3.0 -4.0";
        //when
        double[] actualSphereData = parser.parseSphere(expectedStringData);
        //then
        assertArrayEquals(FIRST_TEST_DATA, actualSphereData,DELTA);
    }

    @Test
    public void shouldReturnExpectedSphereDataListWhenInputIsExpectedStringDataList() {
        //given
        List <String> expectedStringDataList = new ArrayList <>();
        expectedStringDataList.add("1.0 2.7 3.0 -4.0");
        expectedStringDataList.add("5.2 2.1 2.0 2.2");
        expectedStringDataList.add("4.0 6.1 4.0 4.0");
        expectedStringDataList.add("5.0 6.0 2.0 4.0");
        //when
        List <double[]> actualSphereDataList = parser.parseSpheres(expectedStringDataList);
        //then
        assertArrayEquals(FIRST_TEST_DATA,actualSphereDataList.get(0),DELTA);
        assertArrayEquals(SECOND_TEST_DATA,actualSphereDataList.get(1),DELTA);
        assertArrayEquals(THIRD_TEST_DATA,actualSphereDataList.get(2),DELTA);
        assertArrayEquals(FOURTH_TEST_DATA,actualSphereDataList.get(3),DELTA);
    }
}