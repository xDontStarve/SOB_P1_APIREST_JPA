<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import = "java.sql.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Database SQL Load</title>
    </head>
    <style>
        .error {
            color: red;
        }
        pre {
            color: green;
        }
    </style>
    <body>
        <h2>Database SQL Load</h2>
        <%
            /* How to customize:
             * 1. Update the database name on dbname.
             * 2. Create the list of tables, under tablenames[].
             * 3. Create the list of table definition, under tables[].
             * 4. Create the data into the above table, under data[]. 
             * 
             * If there is any problem, it will exit at the very first error.
             */
            String dbname = "sob_grup_25";
            String schema = "ROOT";
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            /* this will generate database if not exist */
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/" + dbname, "root", "root");
            Statement stmt = con.createStatement();
            
            /* inserting data */
            /* you have to exclude the id autogenerated from the list of columns if you have use it. */
            String data[] = new String[]{
                "INSERT INTO " + schema + ".CREDENTIALS VALUES (NEXT VALUE FOR CREDENTIALS_GEN, 'sob', 'sob')",
                "INSERT INTO " + schema + ".GAME (ID, ADDRESS, CONSOLE, DESCRIPTION, GENRE, ISAVAILABLE, NAME, PRICE, UNITS) VALUES (NEXT VALUE FOR GAME_GEN, 'CALLE X', 'NDS', 'FIRST MARIO GAME', 'ADVENTURE', 1, 'SUPER MARIO BROS', 15.99, 3)",
                "INSERT INTO " + schema + ".GAME (ID, ADDRESS, CONSOLE, DESCRIPTION, GENRE, ISAVAILABLE, NAME, PRICE, UNITS) VALUES (NEXT VALUE FOR GAME_GEN, 'CARRER FLORECILLAS', 'PS1', 'JUMPSCARES', 'HORROR', 0, 'CS', 69.99, 1)",
                "INSERT INTO " + schema + ".GAME (ID, ADDRESS, CONSOLE, DESCRIPTION, GENRE, ISAVAILABLE, NAME, PRICE, UNITS) VALUES (NEXT VALUE FOR GAME_GEN, 'PALACIO REAL', 'GB', 'GARLIC OS', 'HORROR', 1, 'SANTIAGO`S PRESENT', 0.99, 5)",
                "INSERT INTO " + schema + ".GAME (ID, ADDRESS, CONSOLE, DESCRIPTION, GENRE, ISAVAILABLE, NAME, PRICE, UNITS) VALUES (NEXT VALUE FOR GAME_GEN, 'BOSNIA TSUKOLEVIA', 'PS4', 'HOSTEL GAME FOR OLDER PEPZ', 'FAMILY', 1, 'BEARS AND BROS IN RUSSIA', 25.50, 10)",
                "INSERT INTO " + schema + ".GAME (ID, ADDRESS, CONSOLE, DESCRIPTION, GENRE, ISAVAILABLE, NAME, PRICE, UNITS) VALUES (NEXT VALUE FOR GAME_GEN, 'REUS', 'GB', 'DONDE VAS CUANDO ESTAS EN UN EXAMEN', 'RACING', 1, 'LA ULTIMA NEURONA', 0.00, 1)",
                "INSERT INTO " + schema + ".CUSTOMER (ID, PASSWORD, USERNAME, EMAIL) VALUES (NEXT VALUE FOR CUSTOMER_GEN, '123456', 'juan', 'juan@gmail.com')",
                "INSERT INTO " + schema + ".CUSTOMER (ID, PASSWORD, USERNAME, EMAIL) VALUES (NEXT VALUE FOR CUSTOMER_GEN, 'PASSWORD', 'JAIME', 'jaimito@hp.com')",
                "INSERT INTO " + schema + ".CUSTOMER (ID, PASSWORD, USERNAME, EMAIL) VALUES (NEXT VALUE FOR CUSTOMER_GEN, 'QWERTY1234', 'JOANA', 'joana@urv.cat')",
                "INSERT INTO " + schema + ".CUSTOMER (ID, PASSWORD, USERNAME, EMAIL) VALUES (NEXT VALUE FOR CUSTOMER_GEN, 'URENEVERGONNAGUESSTHIS', 'PERSON', 'example@example.com')",
                "INSERT INTO " + schema + ".CUSTOMER (ID, PASSWORD, USERNAME, EMAIL) VALUES (NEXT VALUE FOR CUSTOMER_GEN, '88888888.', 'TEST', 'test@test.test')",
                "INSERT INTO " + schema + ".CUSTOMER (ID, PASSWORD, USERNAME, EMAIL) VALUES (NEXT VALUE FOR CUSTOMER_GEN, 'sob', 'sob', 'sob@urv.cat')",
                "INSERT INTO " + schema + ".RENTAL (ID, DATE, PRICE, RETURNDATE, CUSTOMER_ID) VALUES (NEXT VALUE FOR RENTAL_GEN, TIMESTAMP('2023-10-12 15:00:00'), 20.0, TIMESTAMP('2023-12-02 08:15:42'), 1)",
                "INSERT INTO " + schema + ".RENTAL (ID, DATE, PRICE, RETURNDATE, CUSTOMER_ID) VALUES (NEXT VALUE FOR RENTAL_GEN, TIMESTAMP('2023-09-19 12:51:20'), 12.99, TIMESTAMP('2023-12-03 14:30:18'), 2)",
                "INSERT INTO " + schema + ".RENTAL (ID, DATE, PRICE, RETURNDATE, CUSTOMER_ID) VALUES (NEXT VALUE FOR RENTAL_GEN, TIMESTAMP('2023-07-10 10:30:15'), 10.0, TIMESTAMP('2023-12-04 05:45:53'), 3)",
                "INSERT INTO " + schema + ".RENTAL (ID, DATE, PRICE, RETURNDATE, CUSTOMER_ID) VALUES (NEXT VALUE FOR RENTAL_GEN, TIMESTAMP('2023-01-01 19:39:37'), 5.00, TIMESTAMP('2023-12-05 23:20:07'), 4)",
                "INSERT INTO " + schema + ".RENTAL (ID, DATE, PRICE, RETURNDATE, CUSTOMER_ID) VALUES (NEXT VALUE FOR RENTAL_GEN, TIMESTAMP('2023-03-28 19:12:57'), 2.99, TIMESTAMP('2023-12-06 11:10:29'), 2)",
                "INSERT INTO " + schema + ".RENTAL (ID, DATE, PRICE, RETURNDATE, CUSTOMER_ID) VALUES (NEXT VALUE FOR RENTAL_GEN, TIMESTAMP('2022-10-12 16:27:59'), 36.99, TIMESTAMP('2023-12-07 18:55:14'), 2)",
                "INSERT INTO " + schema + ".RENTAL (ID, DATE, PRICE, RETURNDATE, CUSTOMER_ID) VALUES (NEXT VALUE FOR RENTAL_GEN, TIMESTAMP('2021-11-15 17:16:00'), 22.13, TIMESTAMP('2023-12-08 03:40:50'), 1)",
                "INSERT INTO " + schema + ".RENTAL (ID, DATE, PRICE, RETURNDATE, CUSTOMER_ID) VALUES (NEXT VALUE FOR RENTAL_GEN, TIMESTAMP('2023-12-01 21:43:05'), 19.99, TIMESTAMP('2023-12-09 20:05:04'), 4)",
                "INSERT INTO " + schema + ".RENTAL (ID, DATE, PRICE, RETURNDATE, CUSTOMER_ID) VALUES (NEXT VALUE FOR RENTAL_GEN, TIMESTAMP('2023-05-22 09:29:35'), 7.5, TIMESTAMP('2023-12-10 09:55:26'), 1)",
                "INSERT INTO " + schema + ".GAME_RENTAL (RENTAL_ID, GAME_ID) VALUES (1, 5)",
                "INSERT INTO " + schema + ".GAME_RENTAL (RENTAL_ID, GAME_ID) VALUES (2, 4)",
                "INSERT INTO " + schema + ".GAME_RENTAL (RENTAL_ID, GAME_ID) VALUES (2, 3)",
                "INSERT INTO " + schema + ".GAME_RENTAL (RENTAL_ID, GAME_ID) VALUES (4, 2)",
                "INSERT INTO " + schema + ".GAME_RENTAL (RENTAL_ID, GAME_ID) VALUES (4, 3)",
                "INSERT INTO " + schema + ".GAME_RENTAL (RENTAL_ID, GAME_ID) VALUES (7, 4)"/*,
                "INSERT INTO " + schema + ".GAME_RENTAL (RENTAL_ID, GAME_ID) VALUES (7, 5)",
                "INSERT INTO " + schema + ".GAME_RENTAL (RENTAL_ID, GAME_ID) VALUES (1, 2)",
                "INSERT INTO " + schema + ".GAME_RENTAL (RENTAL_ID, GAME_ID) VALUES (2, 1)",
                "INSERT INTO " + schema + ".GAME_RENTAL (RENTAL_ID, GAME_ID) VALUES (3, 1)",
                "INSERT INTO " + schema + ".GAME_RENTAL (RENTAL_ID, GAME_ID) VALUES (5, 5)",
                "INSERT INTO " + schema + ".GAME_RENTAL (RENTAL_ID, GAME_ID) VALUES (6, 2)",
                "INSERT INTO " + schema + ".GAME_RENTAL (RENTAL_ID, GAME_ID) VALUES (9, 3)",
                "INSERT INTO " + schema + ".GAME_RENTAL (RENTAL_ID, GAME_ID) VALUES (8, 4)",
                "INSERT INTO " + schema + ".GAME_RENTAL (RENTAL_ID, GAME_ID) VALUES (7, 1)",
                "INSERT INTO " + schema + ".GAME_RENTAL (RENTAL_ID, GAME_ID) VALUES (5, 4)"
                */
            };
            for (String datum : data) {
                if (stmt.executeUpdate(datum)<=0) {
                    out.println("<span class='error'>Error inserting data: " + datum + "</span>");
                    return;
                }
                out.println("<pre> -> " + datum + "<pre>");
            }
        %>
        <button onclick="window.location='<%=request.getSession().getServletContext().getContextPath()%>'">Go home</button>
    </body>
</html>
