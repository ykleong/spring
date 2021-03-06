# spring
Simple Spring RESTful service.

Simple api to count words within text.

Using :
- Maven (for building and running tests)
- Spring Boot
- Spring Security (for Basic Auth)
- Spring MockMVC (for unit tests)

API :
- GET countapi/top/{limit} - returns high counts of {LIMIT} words + it's number of occurances in CSV format.<br/>
  eg. curl http://host/countapi/top/2 -H"Authorization: Basic dXNlcjpwYXNzMTIz==" -H"Accept: text/csv"<br/>
      <b>result</b>: <br/>
      word1|101<br/>
      word2|92<br/>

- POST countapi/count - returns counts of words for provided words.<br/>
  eg. curl http://{host}/countapi/count -H"Authorization: Basic dXNlcjpwYXNzMTIz" -d'{"searchText":["test", "word", "abc"]}' -H"Content-T Type: application/json" –X POST<br/>
      <b>result</b>: <br/>
      {"counts": [{"test": 12}, {"word": 11}, {"abc": 0}]}

TODO :
- currently the API runs on hardcoded sample data.  This will be upgraded to retreive the text from a local NoSQL database and/or AWS.
- implement custom error messages.
