Feature: Wiremock stub from server

  Scenario: Client1 should return data from server's stub
    Given the kotlin client is ready to accept requests
    When a request is sent to the client1
    Then the data from the server's stub1 is returned

  Scenario: Client1 should return error if no stub is set up
    When a request is sent to the client1
    Then the HTTP status code INTERNAL_SERVER_ERROR is returned by client1

  Scenario: Client2 should return data from server's stub
    Given the java client is ready to accept requests
    When a request is sent to the client2
    Then the data from the server's stub2 is returned

  Scenario: Client2 should return error if no stub is set up
    When a request is sent to the client2
    Then the HTTP status code INTERNAL_SERVER_ERROR is returned by client2
