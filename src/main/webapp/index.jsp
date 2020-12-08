<%@ page import="controller.FileController" %>
<%@ page import="java.nio.file.Path" %>
<!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="utf-8">
            <title>WebDav</title>
        </head>
        <body>
            <div class="container">
                <% for (Path file : new FileController((String)session.getAttribute("username")).getFiles()) { %>
                <%= request.getContextPath() + file.toString() %>
                <% } %>
            </div>
        </body>
</html>