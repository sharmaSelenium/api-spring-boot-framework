# api-spring-boot-framework
API Back-end automation framework using spring dependencies , Junit-5 test runner , allure reporting , and maven build tool

1. Install **Maven** and Import as Maven project in IntelliJ or Eclipse

2. **Framework Structure:**
     - **_apicalls:_** All API Requests
     - **_data:_** Test data - in ENUMS
     - **_dtos:_**  Dynamically Generated Request -Gson and Response DTOS
     - **_expectations:_** Error Response
     - **_providers:_** De-seralized provider methods
     - **_utils:_**  Commonly used methods
     - **_tests:_** All Test cases 
     - **_steps:_** Business logic defined for test cases
     - **_payload:_**  Static json files

3. **Command to run all scenarios:**
    ```bash
    mvn clean test   
    
4. **Test Data Creation Examples:**
    - **Scenario 1:_**
    Hotel Data - QueryHotelSearch enum data and display type enum
    
    - **Scenario 2:_**
    Rooms Guest Mapping is done in RoomGuestMapping enums
    example : TWO_ROOMS_2_ADT_2_CHD_2_ADT_1_CHD_PARIS("Paris","1-2-2#2-3-1")
    2 rooms are separated by # 
    1-2-2 -> represent First room configuration , 1-> Room 1 , 2-> 2 Adults and 2-> 2 Child
    
    - **Dynamically generated test data:_**
     GivenIGenerateSearchHotelPayload(ONE_ROOM_2_ADT_1_CHD_PARIS, 2 ,3), method will take 
     3 arguments
        - 1st arg is room guest mapping as explained above
        - 2nd arg is check-in days from today 
        - 3rd arg is check-out days from today
        
     Error Expectations are covered in expectations folder

5. **Report Generation command: Allure**
    ```bash
    allure generate -c target/allure-results
    ```
    For Windows, 
    
    In order to generate a report, we should install Allure command-line interpreter.
    
    Download the latest version as a zip archive from bintray: https://bintray.com/qameta/generic/allure2/2.7.0#files/io%2Fqameta%2Fallure%2Fallure%2F2.7.0
    Unpack the archive.
    Navigate to the bin directory.
    Add allure’s bin directory to system Path variable.
    
    
