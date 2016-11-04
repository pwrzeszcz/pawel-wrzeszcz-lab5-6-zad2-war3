package com.lab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Controller
public class TransferController extends WebMvcConfigurerAdapter
{
    private static final Logger log = LoggerFactory.getLogger(TransferApplication.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TransferController(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/")
    public String showForm(TransferForm transferForm)
    {
        return "form";
    }

    @PostMapping("/")
    public String checkTransferData(@Valid TransferForm transferForm, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "form";
        }

        log.info("Inserting TransferForm data into transfer table");

        jdbcTemplate.batchUpdate("INSERT INTO transfer" +
                "(name, surname, transfer_title," +
                "account_number) VALUES (?,?,?,?)", new BatchPreparedStatementSetter()
        {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, transferForm.getName());
                ps.setString(2, transferForm.getSurname());
                ps.setString(3, transferForm.getTransferTitle());
                ps.setString(4, transferForm.getAccountNumber());
            }

            @Override
            public int getBatchSize() {
                return 0;
            }
        });

        return "redirect:/results";
    }
}
