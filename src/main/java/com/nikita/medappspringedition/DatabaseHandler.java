package com.nikita.medappspringedition;

import com.nikita.medappspringedition.Configs.DBConfig;

import java.sql.*;

public class DatabaseHandler extends DBConfig {
    public Connection getConnection() throws SQLException {

        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    public ResultSet selectPatName(String id) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL_SELECT_TASKS_FULLNAME = "SELECT \"fullName\" FROM public.\"Patients\" WHERE \"patientId\" =" + id;

        return statement.executeQuery(SQL_SELECT_TASKS_FULLNAME);
    }

    public  ResultSet selectPatMale(String id) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL_SELECT_TASKS_MALE = "SELECT \"male\" FROM public.\"Patients\" WHERE \"patientId\" =" + id;

        return statement.executeQuery(SQL_SELECT_TASKS_MALE);
    }

    public  ResultSet selectPatBirthDate(String id) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL_SELECT_TASKS_BIRTHDATE = "SELECT \"dateOfBirth\" FROM public.\"Patients\" WHERE \"patientId\" =" + id;

        return statement.executeQuery(SQL_SELECT_TASKS_BIRTHDATE);
    }

    public  ResultSet selectPatAge(String id) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL_SELECT_TASKS_AGE = "SELECT \"age\" FROM public.\"Patients\" WHERE \"patientId\" =" + id;

        return statement.executeQuery(SQL_SELECT_TASKS_AGE);
    }

    public  ResultSet selectPatSStatus(String id) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL_SELECT_TASKS_SSTATUS = "SELECT \"socialStatus\" FROM public.\"Patients\" WHERE \"patientId\" =" + id;

        return statement.executeQuery(SQL_SELECT_TASKS_SSTATUS);
    }

    public  ResultSet selectPatLocality(String id) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL_SELECT_TASKS_LOCID = "SELECT \"locId\" FROM public.\"Patients\" WHERE \"patientId\" =" + id;
        ResultSet result1 = statement.executeQuery(SQL_SELECT_TASKS_LOCID);
        result1.next();
        String LocID = result1.getString(1);
        String SQL_SELECT_TASKS_LOCALITY = "SELECT \"locality\" FROM public.\"Location\" WHERE \"locationId\" =" + LocID;

        return statement.executeQuery(SQL_SELECT_TASKS_LOCALITY);
    }

    public  ResultSet selectPatHomeAdress(String id) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL_SELECT_TASKS_LOCID = "SELECT \"locId\" FROM public.\"Patients\" WHERE \"patientId\" =" + id;
        ResultSet result1 = statement.executeQuery(SQL_SELECT_TASKS_LOCID);
        result1.next();
        String LocID = result1.getString(1);
        String SQL_SELECT_TASKS_HADRESS = "SELECT \"homeAdress\" FROM public.\"Location\" WHERE \"locationId\" =" + LocID;

        return statement.executeQuery(SQL_SELECT_TASKS_HADRESS);
    }

    public  ResultSet selectPatDiagnos(String id) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL_SELECT_TASKS_DIAGNOS = "SELECT \"diagnosis\" FROM public.\"MedInfo\" WHERE \"patientId\" =" + id;

        return statement.executeQuery(SQL_SELECT_TASKS_DIAGNOS);
    }

    public  ResultSet selectPatTreatment(String id) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL_SELECT_TASKS_TREATMENT = "SELECT \"treatment\" FROM public.\"MedInfo\" WHERE \"patientId\" =" + id;
        ResultSet result = statement.executeQuery(SQL_SELECT_TASKS_TREATMENT);

        return result;
    }

    public  ResultSet selectPatLastVisit(String id) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL_SELECT_TASKS_LASTVISIT = "SELECT \"lastVisitDate\" FROM public.\"Visits\" WHERE \"patientId\" =" + id;
        ResultSet result = statement.executeQuery(SQL_SELECT_TASKS_LASTVISIT);

        return result;
    }

    public  ResultSet selectPatFirstVisit(String id) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL_SELECT_TASKS_FIRSTVISIT = "SELECT \"firstVisitDate\" FROM public.\"Visits\" WHERE \"patientId\" =" + id;
        ResultSet result = statement.executeQuery(SQL_SELECT_TASKS_FIRSTVISIT);

        return result;
    }

    public  void insertPatInf(String fullName, String male, String dateOfBirth, String age, String socialStatus) throws SQLException {
        Connection connection = getConnection();
        String SQL_INSERT_TASKS_PATINF = "INSERT INTO public.\"Patients\"(\"fullName\", male, \"dateOfBirth\", age, \"socialStatus\")\n" +
                "\tVALUES ('" + fullName + "', '" + male + "'," + "'" + dateOfBirth + "'," + age + ", '" + socialStatus + "');" ;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_TASKS_PATINF);
        preparedStatement.executeUpdate();
    }

    public  void insertLoc(String locality, String homeAdress) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL_SELECT_TASKS_MAXPATID = "SELECT MAX(\"patientId\") FROM public.\"Patients\"";
        ResultSet result = statement.executeQuery(SQL_SELECT_TASKS_MAXPATID);
        result.next();
        int maxPatId = result.getInt(1);

        String SQL_INSERT_TASKS_LOC = "INSERT INTO public.\"Location\"(\"locality\", \"homeAdress\")\n" +
                "\tVALUES ('" + locality + "', '" + homeAdress + "');" ;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_TASKS_LOC);
        preparedStatement.executeUpdate();

        String SQL_SELECT_TASKS_MAXLOCID = "SELECT MAX(\"locationId\") FROM public.\"Location\"";
        ResultSet result1 = statement.executeQuery(SQL_SELECT_TASKS_MAXLOCID);
        result1.next();
        int maxLocId = result1.getInt(1);

        String SQL_UPDATE_LOCID = "UPDATE public.\"Patients\" SET \"locId\"= " + maxLocId +"\tWHERE \"patientId\" = " + maxPatId;
        PreparedStatement preparedStatement1 = connection.prepareStatement(SQL_UPDATE_LOCID);
        preparedStatement1.executeUpdate();
    }

    public void insertMedInfo(String diagnosis, String treatment) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL_SELECT_TASKS_MAXPATID = "SELECT MAX(\"patientId\") FROM public.\"Patients\"";
        ResultSet result = statement.executeQuery(SQL_SELECT_TASKS_MAXPATID);
        result.next();
        int maxPatId = result.getInt(1);

        String SQL_INSERT_TASKS_MEDINFO = "INSERT INTO public.\"MedInfo\"(\"diagnosis\", \"treatment\", \"patientId\")\n" +
                "\tVALUES ('" + diagnosis + "', '" + treatment + "', " + maxPatId + ");" ;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_TASKS_MEDINFO);
        preparedStatement.executeUpdate();
    }

    public void insertVisits(String firstVisitDate, String lastVisitDate) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL_SELECT_TASKS_MAXPATID = "SELECT MAX(\"patientId\") FROM public.\"Patients\"";
        ResultSet result = statement.executeQuery(SQL_SELECT_TASKS_MAXPATID);
        result.next();
        int maxPatId = result.getInt(1);

        String SQL_INSERT_TASKS_VISIT = "INSERT INTO public.\"Visits\"(\"firstVisitDate\", \"lastVisitDate\", \"patientId\")\n" +
                "\tVALUES ('" + firstVisitDate + "', '" + lastVisitDate + "', " + maxPatId + ");" ;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_TASKS_VISIT);
        preparedStatement.executeUpdate();
    }

    public void deletePat(String id) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL_SELECT_TASKS_MAXPATID = "SELECT \"locId\" FROM public.\"Patients\" WHERE \"patientId\" = " + id;
        ResultSet result = statement.executeQuery(SQL_SELECT_TASKS_MAXPATID);
        result.next();
        int locId = result.getInt(1);

        String SQL_INSERT_TASKS_DELETEVISIT = "DELETE FROM public.\"Visits\"\n" +
                "\tWHERE \"patientId\" =" + id;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_TASKS_DELETEVISIT);
        preparedStatement.executeUpdate();

        String SQL_INSERT_TASKS_DELELTEMEDINFO = "DELETE FROM public.\"MedInfo\"\n" +
                "\tWHERE \"patientId\" =" + id;
        PreparedStatement preparedStatement1 = connection.prepareStatement(SQL_INSERT_TASKS_DELELTEMEDINFO);
        preparedStatement1.executeUpdate();

        String SQL_INSERT_TASKS_DELELTELOCATION = "DELETE FROM public.\"Location\"\n" +
                "\tWHERE \"locationId\" =" + locId;
        PreparedStatement preparedStatement2 = connection.prepareStatement(SQL_INSERT_TASKS_DELELTELOCATION);
        preparedStatement2.executeUpdate();

        String SQL_INSERT_TASKS_DELELTEPAT = "DELETE FROM public.\"Patients\"\n" +
                "\tWHERE \"patientId\" =" + id;
        PreparedStatement preparedStatement3 = connection.prepareStatement(SQL_INSERT_TASKS_DELELTEPAT);
        preparedStatement3.executeUpdate();
    }

    public void updatePat(String fullName, String male, String dateOfBirth, String age, String socialStatus, String id) throws SQLException{
        Connection connection = getConnection();
        String SQL_UPDATE_TASKS_PATINF = "UPDATE public.\"Patients\"\n" +
                "\tSET \"fullName\"='" + fullName + "', male='" + male + "', \"dateOfBirth\"='" + dateOfBirth + "', age= " + age + ", \"socialStatus\"='" + socialStatus + "'\n" +
                "\tWHERE \"patientId\" = " + id;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_TASKS_PATINF);
        preparedStatement.executeUpdate();
    }

    public void updateLoc(String locality, String homeAdress, String id) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL_SELECT_TASKS_MAXPATID = "SELECT \"locId\" FROM public.\"Patients\" WHERE \"patientId\" = " + id;
        ResultSet result = statement.executeQuery(SQL_SELECT_TASKS_MAXPATID);
        result.next();
        int locId = result.getInt(1);

        String SQL_UPDATE_TASKS_LOC = "UPDATE public.\"Location\"\n" +
                "\tSET \"locality\"='" + locality + "', \"homeAdress\"='" + homeAdress + "'\n" +
                "\tWHERE \"locationId\" = " + locId;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_TASKS_LOC);
        preparedStatement.executeUpdate();
    }

    public void updateMedInfo(String diagnosis, String treatment, String id) throws SQLException {
        Connection connection = getConnection();
        String SQL_UPDATE_TASKS_MEDINFO = "UPDATE public.\"MedInfo\"\n" +
                "\tSET \"diagnosis\"='" + diagnosis + "', \"treatment\"='" + treatment + "'\n" +
                "\tWHERE \"patientId\" = " + id;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_TASKS_MEDINFO);
        preparedStatement.executeUpdate();
    }

    public void updateVisits(String firstVisitDate, String lastVisitDate, String id) throws SQLException {
        Connection connection = getConnection();
        String SQL_UPDATE_TASKS_VISITS = "UPDATE public.\"Visits\"\n" +
                "\tSET \"firstVisitDate\"='" + firstVisitDate + "', \"lastVisitDate\"='" + lastVisitDate + "'\n" +
                "\tWHERE \"patientId\" = " + id;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_TASKS_VISITS);
        preparedStatement.executeUpdate();
    }

    public ResultSet selectIDs() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL_SELECT_TASKS_IDS = "SELECT \"patientId\" FROM public.\"Patients\"";
        ResultSet result = statement.executeQuery(SQL_SELECT_TASKS_IDS);

        return result;
    }

    public ResultSet selectNames() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL_SELECT_TASKS_NAMES = "SELECT \"fullName\" FROM public.\"Patients\"";
        ResultSet result = statement.executeQuery(SQL_SELECT_TASKS_NAMES);

        return result;
    }

    public ResultSet selectMales() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL_SELECT_TASKS_MALES = "SELECT \"male\" FROM public.\"Patients\"";
        ResultSet result = statement.executeQuery(SQL_SELECT_TASKS_MALES);
        return result;
    }

    public ResultSet selectDatesOfBirths() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL_SELECT_TASKS_BDATES = "SELECT \"dateOfBirth\" FROM public.\"Patients\"";
        ResultSet result = statement.executeQuery(SQL_SELECT_TASKS_BDATES);

        return result;
    }

    public ResultSet selectAges() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL_SELECT_TASKS_AGES = "SELECT \"age\" FROM public.\"Patients\"";
        ResultSet result = statement.executeQuery(SQL_SELECT_TASKS_AGES);

        return result;
    }

    public ResultSet selectSocialStatuses() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL_SELECT_TASKS_SSTATUSES = "SELECT \"socialStatus\" FROM public.\"Patients\"";
        ResultSet result = statement.executeQuery(SQL_SELECT_TASKS_SSTATUSES);

        return result;
    }

    public ResultSet selectLocalities() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL_SELECT_TASKS_LOCALITIES = "SELECT \"locality\" FROM public.\"Location\"";
        ResultSet result = statement.executeQuery(SQL_SELECT_TASKS_LOCALITIES);

        return result;
    }

    public ResultSet selectHomeAdresses() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL_SELECT_TASKS_HADRESSES = "SELECT \"homeAdress\" FROM public.\"Location\"";
        ResultSet result = statement.executeQuery(SQL_SELECT_TASKS_HADRESSES);

        return result;
    }

    public ResultSet selectDiagnosis() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL_SELECT_TASKS_DIAGNOSIS = "SELECT \"diagnosis\" FROM public.\"MedInfo\"";
        ResultSet result = statement.executeQuery(SQL_SELECT_TASKS_DIAGNOSIS);

        return result;
    }

    public ResultSet selectTreatments() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL_SELECT_TASKS_TREATMENTS = "SELECT \"treatment\" FROM public.\"MedInfo\"";
        ResultSet result = statement.executeQuery(SQL_SELECT_TASKS_TREATMENTS);

        return result;
    }

    public ResultSet selectLastVisitsDates() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL_SELECT_TASKS_LASTVISITS = "SELECT \"lastVisitDate\" FROM public.\"Visits\"";
        ResultSet result = statement.executeQuery(SQL_SELECT_TASKS_LASTVISITS);

        return result;
    }

    public ResultSet selectFirstVisitsDates() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL_SELECT_TASKS_FIRSTVISITS = "SELECT \"firstVisitDate\" FROM public.\"Visits\"";
        ResultSet result = statement.executeQuery(SQL_SELECT_TASKS_FIRSTVISITS);

        return result;
    }
}

