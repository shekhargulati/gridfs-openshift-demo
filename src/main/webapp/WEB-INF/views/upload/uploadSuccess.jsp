<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>Upload Successful to MongoDB GridFS</title>
    </head>
    <body>
        <p>Upload successful to MongoDB GridFS. SSH into your instance and run 
        <code>
        mongofiles -d gridfs list -u $OPENSHIFT_MONGODB_DB_USERNAME -p $OPENSHIFT_MONGODB_DB_PASSWORD -h $OPENSHIFT_MONGODB_DB_HOST -port $OPENSHIFT_MONGODB_DB_PORT
        </code>
        </p>
    </body>
</html>