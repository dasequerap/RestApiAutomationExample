package helpers.constants;

public class Configurations {

    public enum CommonKeys {
        CONFIGURATION_FILES,
        TEST_DATA_FILES,
        DIRECTORY,
        SERVICES_MAPPING_FILE,
    }

    public enum TestConfigurations {
        CSV_COLUMN_SEPARATOR,
        CSV_ARRAY_SEPARATOR
    }

    public enum ServiceResources {
        PEOPLE,
        FILMS
    }

    public enum ServiceParameters {
        EXPECTED_RESPONSE_CODE,
        QUERY_PARAMETERS,
        FIELDS_MULTIPLE_RESULTS,
        FIELDS_SINGLE_RESULT
    }
}
