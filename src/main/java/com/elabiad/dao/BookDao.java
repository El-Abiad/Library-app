package com.elabiad.dao;

import com.elabiad.config.Database;
import com.elabiad.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BookDao {

    public void add(Book book) throws SQLException {
        String sql = """
                INSERT INTO books(title, author, published_year)
                VALUES(?, ?, ?)
                """;
        try(
                Connection connection = Database.connect();
                PreparedStatement statement = connection.prepareStatement(sql)
                ){
                statement.setString(1, book.getTitle());
                statement.setString(2, book.getAuthor());
                statement.setInt(3, book.getPublishedYear());

                statement.executeUpdate();
        }
    }

    public List<Book> getAll() throws SQLException{
        String sql = """
                SELECT * FROM books;
                """;
        List<Book> books = new ArrayList<>();
        try(
                Connection connection = Database.connect();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rs = statement.executeQuery()
                ){
            while(rs.next()){
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("published_year")
                );
                books.add(book);
            }
        }
        return books;
    }

    public Book findById(int id) throws SQLException{
        String sql = """
                SELECT * FROM books WHERE id = ?;
                """;
        try(
                Connection connection = Database.connect();
                PreparedStatement statement = connection.prepareStatement(sql)
                ){
            statement.setInt(1, id);
            try(ResultSet rs = statement.executeQuery()){
                if(rs.next()){
                    return new Book(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getInt("published_year")
                    );
                }
            }

        }
        return null;
    }

    public boolean delete(int id) throws SQLException{
        String sql = """
                DELETE FROM books WHERE id = ?;
                """;
        try(
                Connection connection = Database.connect();
                PreparedStatement statement = connection.prepareStatement(sql)
                ) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
