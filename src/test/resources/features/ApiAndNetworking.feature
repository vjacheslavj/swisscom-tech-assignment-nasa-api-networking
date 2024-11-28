Feature: NASA API and Networking Validation
  Validate interactions with NASA API and networking operations.

  @NASA_API
  Scenario: Validate NASA API response
    Given I send a request to the NASA API with the "yesterday" date
    Then the HTTP response code should be 200
    Then the response should include "title", "explanation", "url", "media_type" and "date"
    And the media_type should be either "image" or "video"

  @Networking
  Scenario: Validate Networking Operations
    When I retrieve my public IP address
    Then it should not be in the range 101.33.28.0 - 101.33.29.0
    When I resolve the domain "google-public-dns-a.google.com"
    Then the resolved IP should be "8.8.8.8"
    When I perform a traceroute to "8.8.8.8"
    Then the target should be reached within 10 hops
