
import org.junit.Test;
import org.junit.experimental.results.ResultMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class JsonWriterTest {


    @ParameterizedTest
    @MethodSource("listToJSONMethodSource")
    public void listToJSONTest(List<Employee> a, String expectedResult) {
        JsonWriter jsw = JsonWriter.get();
        String result = jsw.listToJSON(a);
        Assertions.assertEquals(expectedResult, result);
    }

    static public Stream<Arguments> listToJSONMethodSource() {

        return Stream.of(
                Arguments.of(Arrays.asList(
                                new Employee(1, "John", "Smith", "USA", 25),
                                new Employee(2, "Ivan", "Petrov", "RU", 23))
                        , "[{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"age\":25},{\"id\":2,\"firstName\":\"Ivan\",\"lastName\":\"Petrov\",\"country\":\"RU\",\"age\":23}]"),

                Arguments.of(Arrays.asList(
                        new Employee(1, "John", "Smith", "USA", 25)
                ), "[{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"age\":25}]"),

                Arguments.of(new ArrayList<>(), "[]"),
                Arguments.of(null, "null")
        );
    }
}
