package com.kuartz.core.data.jpa.initializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.*;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.LineNumberReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class KuartzScriptUtil extends ScriptUtils {

    public static final Logger LOG = LoggerFactory.getLogger(KuartzScriptUtil.class);

    public static void executeSqlScript(Connection connection, EncodedResource resource, boolean continueOnError,
                                        boolean ignoreFailedDrops, String commentPrefix, String separator,
                                        String blockCommentStartDelimiter, String blockCommentEndDelimiter) throws ScriptException {

        try {
            LOG.info(resource + " SQL dosyasi calistiriliyor.");
            long startTime = System.currentTimeMillis();

            String script;
            try {
                script = readScript(resource, commentPrefix, separator, blockCommentEndDelimiter);
            } catch (IOException ex) {
                throw new CannotReadScriptException(resource, ex);
            }

            if (separator == null) {
                separator = DEFAULT_STATEMENT_SEPARATOR;
            }
            if (!EOF_STATEMENT_SEPARATOR.equals(separator) && !containsSqlScriptDelimiters(script, separator)) {
                separator = FALLBACK_STATEMENT_SEPARATOR;
            }

            List<String> statements = new ArrayList<>();
            splitSqlScript(resource, script, separator, commentPrefix, blockCommentStartDelimiter,
                           blockCommentEndDelimiter, statements);

            int stmtNumber = 0;
            long rowsAffected = 0;
            Statement stmt = connection.createStatement();
            try {
                for (String statement : statements) {
                    stmtNumber++;
                    try {
                        stmt.execute(statement);
                        LOG.info(statement + " calistirildi.");

                        // todo bunu inceleyelim.
                        rowsAffected += stmt.getUpdateCount();

                        SQLWarning warningToLog = stmt.getWarnings();
                        while (warningToLog != null) {
                            LOG.warn(warningToLog.getSQLState() + " " + warningToLog.getErrorCode() + " " + warningToLog.getMessage() +
                                     " hatasi esgecildi");
                            warningToLog = warningToLog.getNextWarning();
                        }
                    } catch (SQLException ex) {
                        boolean dropStatement = StringUtils.startsWithIgnoreCase(statement.trim(), "drop");
                        if (continueOnError || (dropStatement && ignoreFailedDrops)) {
                            LOG.warn("Satir calistirilamadi : " + statement);
                            LOG.error("SQL Exception : ", ex);
                        } else {
                            throw new ScriptStatementFailedException(statement, stmtNumber, resource, ex);
                        }
                    }
                }
            } finally {
                try {
                    stmt.close();
                } catch (Throwable ex) {
                    LOG.trace("Could not close JDBC Statement", ex);
                }
            }

            long elapsedTime = System.currentTimeMillis() - startTime;
            LOG.info(resource.getResource().getFilename() + " --> " + elapsedTime + " ms." + " 'de calistiridi. " + rowsAffected +
                     " satir guncellendi.");
        } catch (Exception ex) {
            if (ex instanceof ScriptException) {
                throw (ScriptException) ex;
            }
            throw new UncategorizedScriptException(
                    "Failed to execute database script from resource [" + resource.getResource().getFilename() + "]", ex);
        }
    }


    private static String readScript(EncodedResource resource, String commentPrefix, String separator,
                                     String blockCommentEndDelimiter) throws IOException {

        try (LineNumberReader lnr = new LineNumberReader(resource.getReader())) {
            return readScript(lnr, commentPrefix, separator, blockCommentEndDelimiter);
        }
    }

}
