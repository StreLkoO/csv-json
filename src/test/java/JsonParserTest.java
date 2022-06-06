
import org.junit.experimental.results.ResultMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Stream;

public class JsonParserTest {


    @Test
    public void readStringNullTest() {
        JsonParser jsp = JsonParser.get();
        Assertions.assertNull(jsp.readString(""));

    }

    @ParameterizedTest
    @MethodSource("jsonToListMethodSource")
    public void jsonToListTest(String json, List<Employee> expectedResult) {
        JsonParser jsp = JsonParser.get();
        List<Employee> result = jsp.jsonToList(json);
        Assertions.assertEquals(expectedResult, result);
    }

    static public Stream<Arguments> jsonToListMethodSource() {
        return Stream.of(
                Arguments.of(
                        "[{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"age\":25},{\"id\":2,\"firstName\":\"Ivan\",\"lastName\":\"Petrov\",\"country\":\"RU\",\"age\":23}]",
                        new ArrayList<>(Arrays.asList(
                                new Employee(1, "John", "Smith", "USA", 25),
                                new Employee(2, "Ivan", "Petrov", "RU", 23)))
                ),

                Arguments.of(
                        "[{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"age\":25}]",
                        new ArrayList<>(Arrays.asList(
                                new Employee(1, "John", "Smith", "USA", 25)
                        ))
                )
        );

    }

}
