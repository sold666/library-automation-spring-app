package client;

import java.util.Scanner;

import io.jsonwebtoken.JwtException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;

public class ClientApp {

    public static void main(String[] args) throws ParseException {
        Scanner in = new Scanner(System.in);
        String token = "";
        System.out.print("Hi! This is a client created to process your requests, to find out what I can do, enter: help\n");
        while (true) {
            System.out.print("Enter a request: ");
            String str = in.nextLine();

            try {
                if (str.equals(("help"))) {
                    System.out.print("""
                                                        
                            sign_in - authorization
                            ----------------------------
                            journals - find all journals
                            find_journal- find journal by id
                            find_journal_by_book - find journal by book id
                            find_journal_by_client - find journal by client id
                            update_date_journal - updates the end date of the book in the journal
                            add_journal - add journal
                            delete_journal - delete journal by id
                            delete_journal_by_client - delete journal by client name
                            ----------------------------
                            clients - find all clients
                            find_client - find client by id
                            find_client_by_name- find client by client name
                            find_journal_by_client - find journal by client id
                            client_fine - outputs sum fines client
                            client_days - outputs days client
                            client_num - outputs number of customer's books
                            add_client - add client
                            delete_client - delete client by id
                            ----------------------------
                            books_types - find all books types
                            find_books_types - find books types by id
                            find_books_types_by_name - find books types by name
                            update_book_type_days - update days in book type
                            update_book_type_cnt - update count of book type
                            add_book_type - add book type
                            delete_book_type - delete book type by name
                            ----------------------------
                            books - find all books
                            find_books - find books types by id
                            find_books_by_name - find books types by name
                            find_book_client - find the client's book
                            update_book_cnt - update count of book
                            add_book - add book type
                            delete_book - delete book type by name
                                                        
                            """
                    );
                }
                if (str.equals("sign_in")) {
                    System.out.print("Enter login: ");
                    String name = in.nextLine();
                    System.out.print("Enter password: ");
                    String pass = in.nextLine();

                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/auth/sign_in";
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    HttpEntity<String> request = new HttpEntity<>("{\n" +
                            "    \"userName\": \"" + name + "\",\n" +
                            "    \"password\": \"" + pass + "\"\n" +
                            "}", headers);
                    ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);

                    Object obj = new JSONParser().parse(responseEntity.getBody());
                    JSONObject jo = (JSONObject) obj;
                    token = (String) jo.get("token");
                    if (responseEntity.getStatusCode().toString().equals("200 OK")) {
                        System.out.println("Authentication passed");
                    }
                }

                if (str.equals("journals")) {
                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/jc/journals";
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Authorization", "Bearer " + token);
                    HttpEntity<String> request = new HttpEntity<>(headers);
                    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
                    if (responseEntity.getStatusCode().toString().equals("200 OK")) {
                        System.out.println(responseEntity.getBody());
                    }
                }
                if (str.equals("find_journal")) {
                    System.out.print("Enter id: ");
                    String id = in.nextLine();
                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/jc/" + id;
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Authorization", "Bearer " + token);
                    HttpEntity<String> request = new HttpEntity<>(headers);
                    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
                    if (responseEntity.getStatusCode().toString().equals("200 OK")) {
                        System.out.println(responseEntity.getBody());
                    }
                }
                if (str.equals("find_journal_by_book")) {
                    System.out.print("Enter book id: ");
                    String id = in.nextLine();
                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/jc/book/" + id;
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Authorization", "Bearer " + token);
                    HttpEntity<String> request = new HttpEntity<>(headers);
                    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
                    if (responseEntity.getStatusCode().toString().equals("200 OK")) {
                        System.out.println(responseEntity.getBody());
                    }
                }
                if (str.equals("find_journal_by_client")) {
                    System.out.print("Enter client id: ");
                    String id = in.nextLine();
                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/jc/client/" + id;
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Authorization", "Bearer " + token);
                    HttpEntity<String> request = new HttpEntity<>(headers);
                    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
                    if (responseEntity.getStatusCode().toString().equals("200 OK")) {
                        System.out.println(responseEntity.getBody());
                    }
                }
                if (str.equals("add_journal")) {
                    System.out.print("-Information about book-\n");
                    System.out.print("Enter id: ");
                    String book_id = in.nextLine();
                    System.out.print("Enter book name: ");
                    String name = in.nextLine();
                    System.out.print("Enter count: ");
                    String book_count = in.nextLine();

                    System.out.print("-Information about type of book-\n");
                    System.out.print("Enter id: ");
                    String book_type_id = in.nextLine();
                    System.out.print("Enter type name: ");
                    String type_name = in.nextLine();
                    System.out.print("Enter count: ");
                    String count = in.nextLine();
                    System.out.print("Enter fine: ");
                    String fine = in.nextLine();
                    System.out.print("Enter days count: ");
                    String day_count = in.nextLine();

                    System.out.print("-Information about client-\n");
                    System.out.print("Enter first name: ");
                    String first_name = in.nextLine();
                    System.out.print("Enter last name: ");
                    String last_name = in.nextLine();
                    System.out.print("Enter father name: ");
                    String father_name = in.nextLine();
                    System.out.print("Enter passport seria: ");
                    String passport_seria = in.nextLine();
                    System.out.print("Enter passport number: ");
                    String passport_number = in.nextLine();

                    System.out.print("Enter date beg: ");
                    String date_beg = in.nextLine();
                    System.out.print("Enter date end: ");
                    String date_end = in.nextLine();
                    System.out.print("Enter date reg: ");
                    String date_reg = in.nextLine();

                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/jc/add journal";
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    headers.add("Authorization", "Bearer " + token);
                    HttpEntity<String> request = new HttpEntity<>("{ \"book\": {\n" +
                            "\"id\": \"" + book_id + "\",\n" +
                            "\"name\": \"" + name + "\",\n" +
                            "\"cnt\": \"" + book_count + "\",\n" +
                            "\"type\":" + "{\n" +
                            "\"id\": \"" + book_type_id + "\",\n" +
                            "\"name\": \"" + type_name + "\",\n" +
                            "\"cnt\": \"" + count + "\",\n" +
                            "\"fine\": \"" + fine + "\",\n" +
                            "\"dayCount\": \"" + day_count + "\"\n" + "}\n" +
                            "},\n" +
                            "\"client\":" + "{\n" +
                            "\"firstName\": \"" + first_name + "\",\n" +
                            "\"lastName\": \"" + last_name + "\",\n" +
                            "\"patherName\": \"" + father_name + "\",\n" +
                            "\"passportSeria\": \"" + passport_seria + "\",\n" +
                            "\"passportNum\": \"" + passport_number + "\"\n" +
                            "},\n" +
                            "\"dateBeg\": " + date_beg + ",\n" +
                            "\"dateEnd\": " + date_end + ",\n" +
                            "\"dateReg\": " + date_reg + "\n" + "}", headers);

                    try {
                        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
                        if (responseEntity.getStatusCode().toString().equals("200 OK")) {
                            System.out.println("Successfully added");
                        }
                    } catch (HttpServerErrorException.InternalServerError ignored ) {
                    } catch (HttpClientErrorException e) {
                        System.out.println("Not added");
                    }
                }
                if (str.equals("update_date_journal")) {
                    System.out.print("Enter id: ");
                    String id = in.nextLine();
                    System.out.print("Enter date end in format yyyy-MM-dd HH:mm:ss: ");
                    String date = in.nextLine();

                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/jc/update/" + id + "/date?dateEnd=" + date;
                    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
                    restTemplate.setRequestFactory(new
                            HttpComponentsClientHttpRequestFactory(httpClient));
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Authorization", "Bearer " + token);
                    HttpEntity<String> request = new HttpEntity<>(headers);
                    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PATCH, request, String.class);
                    if (responseEntity.getStatusCode().toString().equals("200 OK")) {
                        System.out.println("Successfully updated");
                    }
                }
                if (str.equals("delete_journal")) {
                    System.out.print("Enter id: ");
                    String id = in.nextLine();
                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/jc/" + id;
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Authorization", "Bearer " + token);
                    HttpEntity<String> request = new HttpEntity<>(headers);
                    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
                    if (responseEntity.getStatusCode().toString().equals("200 OK")) {
                        System.out.println("The journal has been deleted successfully");
                    }
                }
                if (str.equals("delete_journal_by_client")) {
                    System.out.print("Enter id: ");
                    String id = in.nextLine();
                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/jc/client/" + id;
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Authorization", "Bearer " + token);
                    HttpEntity<String> request = new HttpEntity<>(headers);
                    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
                    if (responseEntity.getStatusCode().toString().equals("200 OK")) {
                        System.out.println("The journal has been deleted successfully");
                    }
                }

                if (str.equals("clients")) {
                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/cc/clients";
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Authorization", "Bearer " + token);
                    HttpEntity<String> request = new HttpEntity<>(headers);
                    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
                    if (responseEntity.getStatusCode().toString().equals("200 OK")) {
                        System.out.println(responseEntity.getBody());
                    }
                }
                if (str.equals("find_client")) {
                    System.out.print("Enter id: ");
                    String id = in.nextLine();
                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/cc/" + id;
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Authorization", "Bearer " + token);
                    HttpEntity<String> request = new HttpEntity<>(headers);
                    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
                    if (responseEntity.getStatusCode().toString().equals("200 OK")) {
                        System.out.println(responseEntity.getBody());
                    }
                }
                if (str.equals("find_client_by_name")) {
                    System.out.print("Enter first name: ");
                    String first_name = in.nextLine();
                    System.out.print("Enter last name: ");
                    String last_name = in.nextLine();
                    System.out.print("Enter father name: ");
                    String father_name = in.nextLine();
                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/cc/full name" + "?firstname=" + first_name + "&lastname=" + last_name
                            + "&pathername=" + father_name;
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Authorization", "Bearer " + token);
                    HttpEntity<String> request = new HttpEntity<>(headers);
                    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
                    if (responseEntity.getStatusCode().toString().equals("200 OK")) {
                        System.out.println(responseEntity.getBody());
                    }
                }
                if (str.equals("client_fine")) {
                    System.out.print("Enter first name: ");
                    String first_name = in.nextLine();
                    System.out.print("Enter last name: ");
                    String last_name = in.nextLine();
                    System.out.print("Enter father name: ");
                    String father_name = in.nextLine();
                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/cc/full name/fine" + "?firstname=" + first_name + "&lastname=" + last_name
                            + "&pathername=" + father_name;
                    ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
                    System.out.println(responseEntity.getBody());
                }
                if (str.equals("client_days")) {
                    System.out.print("Enter first name: ");
                    String first_name = in.nextLine();
                    System.out.print("Enter last name: ");
                    String last_name = in.nextLine();
                    System.out.print("Enter father name: ");
                    String father_name = in.nextLine();
                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/cc/full name/days" + "?firstname=" + first_name + "&lastname=" + last_name
                            + "&pathername=" + father_name;
                    ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
                    System.out.println(responseEntity.getBody());
                }
                if (str.equals("client_num")) {
                    System.out.print("Enter first name: ");
                    String first_name = in.nextLine();
                    System.out.print("Enter last name: ");
                    String last_name = in.nextLine();
                    System.out.print("Enter father name: ");
                    String father_name = in.nextLine();
                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/cc/full name/num" + "?firstname=" + first_name + "&lastname=" + last_name
                            + "&pathername=" + father_name;
                    ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
                    System.out.println(responseEntity.getBody());
                }
                if (str.equals("add_client")) {
                    System.out.print("Enter first name: ");
                    String first_name = in.nextLine();
                    System.out.print("Enter last name: ");
                    String last_name = in.nextLine();
                    System.out.print("Enter father name: ");
                    String father_name = in.nextLine();
                    System.out.print("Enter passport seria: ");
                    String passport_seria = in.nextLine();
                    System.out.print("Enter passport number: ");
                    String passport_number = in.nextLine();

                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/cc/add client";
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    headers.add("Authorization", "Bearer " + token);
                    HttpEntity<String> request = new HttpEntity<>("{\n" +
                            "\"firstName\": \"" + first_name + "\",\n" +
                            "\"lastName\": \"" + last_name + "\",\n" +
                            "\"patherName\": \"" + father_name + "\",\n" +
                            "\"passportSeria\": \"" + passport_seria + "\",\n" +
                            "\"passportNum\": \"" + passport_number + "\"\n" +
                            "}", headers);
                    ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
                    if (responseEntity.getStatusCode().toString().equals("200 OK")) {
                        System.out.println("Successfully added");
                    }
                }
                if (str.equals("delete_client")) {
                    System.out.print("Enter id: ");
                    String id = in.nextLine();
                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/cc/" + id;
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Authorization", "Bearer " + token);
                    HttpEntity<String> request = new HttpEntity<>(headers);
                    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
                    if (responseEntity.getStatusCode().toString().equals("200 OK")) {
                        System.out.println("The client has been deleted successfully");
                    }
                }

                if (str.equals("books_types")) {
                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/btc/books type";
                    ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
                    System.out.println(responseEntity.getBody());
                }
                if (str.equals("find_books_types")) {
                    System.out.print("Enter id: ");
                    String id = in.nextLine();
                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/btc/" + id;
                    ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
                    System.out.println(responseEntity.getBody());
                }
                if (str.equals("find_books_types_by_name")) {
                    System.out.print("Enter name: ");
                    String name = in.nextLine();
                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/btc/name/" + name;
                    ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
                    System.out.println(responseEntity.getBody());
                }
                if (str.equals("update_book_type_days")) {
                    System.out.print("Enter id: ");
                    String id = in.nextLine();
                    System.out.print("Enter days of book type: ");
                    String days = in.nextLine();

                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/btc/update/" + id + "/days?days_count=" + days;
                    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
                    restTemplate.setRequestFactory(new
                            HttpComponentsClientHttpRequestFactory(httpClient));
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Authorization", "Bearer " + token);
                    HttpEntity<String> request = new HttpEntity<>(headers);
                    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PATCH, request, String.class);
                    if (responseEntity.getStatusCode().toString().equals("200 OK")) {
                        System.out.println("Successfully updated");
                    }
                }
                if (str.equals("update_book_type_cnt")) {
                    System.out.print("Enter id: ");
                    String id = in.nextLine();
                    System.out.print("Enter count of book type: ");
                    String count = in.nextLine();

                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/btc/update/" + id + "/cnt?cnt=" + count;
                    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
                    restTemplate.setRequestFactory(new
                            HttpComponentsClientHttpRequestFactory(httpClient));
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Authorization", "Bearer " + token);
                    HttpEntity<String> request = new HttpEntity<>(headers);
                    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PATCH, request, String.class);
                    if (responseEntity.getStatusCode().toString().equals("200 OK")) {
                        System.out.println("Successfully updated");
                    }
                }
                if (str.equals("add_book_type")) {
                    System.out.print("Enter name book type: ");
                    String type = in.nextLine();
                    System.out.print("Enter number of types: ");
                    String types_count = in.nextLine();
                    System.out.print("Enter fine of type: ");
                    String fine = in.nextLine();
                    System.out.print("Enter day count type of book: ");
                    String day_count = in.nextLine();

                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/btc/add_type";
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    headers.add("Authorization", "Bearer " + token);
                    HttpEntity<String> request = new HttpEntity<>("{\n" +
                            "\"name\": \"" + type + "\",\n" +
                            "\"cnt\": \"" + types_count + "\",\n" +
                            "\"fine\": \"" + fine + "\",\n" +
                            "\"dayCount\": \"" + day_count + "\"\n" +
                            "}", headers);
                    ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
                    if (responseEntity.getStatusCode().toString().equals("200 OK")) {
                        System.out.println("Successfully added");
                    }
                }
                if (str.equals("delete_book_type")) {
                    System.out.print("Enter name: ");
                    String name = in.nextLine();
                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/btc/" + name;
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Authorization", "Bearer " + token);
                    HttpEntity<String> request = new HttpEntity<>(headers);
                    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
                    if (responseEntity.getStatusCode().toString().equals("200 OK")) {
                        System.out.println("The book type has been deleted successfully");
                    }
                }

                if (str.equals("books")) {
                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/bc/books";
                    ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
                    System.out.println(responseEntity.getBody());
                }
                if (str.equals("find_books")) {
                    System.out.print("Enter id: ");
                    String id = in.nextLine();
                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/bc/" + id;
                    ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
                    System.out.println(responseEntity.getBody());
                }
                if (str.equals("find_books_by_name")) {
                    System.out.print("Enter name: ");
                    String name = in.nextLine();
                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/bc/name/" + name;
                    ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
                    System.out.println(responseEntity.getBody());
                }
                if (str.equals("find_book_client")) {
                    System.out.print("Enter first name: ");
                    String first_name = in.nextLine();
                    System.out.print("Enter last name: ");
                    String last_name = in.nextLine();
                    System.out.print("Enter father name: ");
                    String father_name = in.nextLine();
                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/bc/search_client_book" + "?firstname=" + first_name + "&lastname=" + last_name
                            + "&pathername=" + father_name;
                    ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
                    System.out.println(responseEntity.getBody());
                }
                if (str.equals("update_book_cnt")) {
                    System.out.print("Enter id: ");
                    String id = in.nextLine();
                    System.out.print("Enter count: ");
                    String count = in.nextLine();
                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/bc/update/" + id + "/cnt?cnt=" + count;

                    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
                    restTemplate.setRequestFactory(new
                            HttpComponentsClientHttpRequestFactory(httpClient));
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Authorization", "Bearer " + token);
                    HttpEntity<String> request = new HttpEntity<>(headers);
                    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PATCH, request, String.class);
                    if (responseEntity.getStatusCode().toString().equals("200 OK")) {
                        System.out.println("Successfully updated");
                    }
                }
                if (str.equals("add_book")) {
                    System.out.print("Enter book name: ");
                    String name = in.nextLine();
                    System.out.print("Enter number of books: ");
                    String book_count = in.nextLine();
                    System.out.print("Enter id type of book: ");
                    String id = in.nextLine();

                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/bc/add_book";
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    headers.add("Authorization", "Bearer " + token);
                    HttpEntity<String> request = new HttpEntity<>("{\n" +
                            "\"name\": \"" + name + "\",\n" +
                            "\"cnt\": \"" + book_count + "\",\n" +
                            "\"type\":" + "{\n" +
                            "\"id\": \"" + id + "\"\n" + "}\n" +
                            "}", headers);
                    ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
                    if (responseEntity.getStatusCode().toString().equals("200 OK")) {
                        System.out.println("Successfully added");
                    }
                }
                if (str.equals("delete_book")) {
                    System.out.print("Enter name: ");
                    String name = in.nextLine();
                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8080/bc/" + name;
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Authorization", "Bearer " + token);
                    HttpEntity<String> request = new HttpEntity<>(headers);
                    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
                    if (responseEntity.getStatusCode().toString().equals("200 OK")) {
                        System.out.println("The book has been deleted successfully");
                    }
                }
            }
            catch (HttpClientErrorException e) {
                e.getResponseBodyAsString();
            }
            catch (ResourceAccessException e) {
                System.err.println("Access denied");
            }
            catch (JwtException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
