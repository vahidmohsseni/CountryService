

def json_to_sql_from_file(file_to_read, file_to_write):
    with open(file_to_read, "r") as fi:
        data = fi.read()
        data = data.replace("'", "''")
        data = eval(data)
        with open (file_to_write, "w") as fi2:
            for i in data:
                fi2.write("INSERT INTO country (name, country_code, capital, population, flag_file_url) ")
                fi2.write("VALUES (\'" + i['name'] + "\', ")
                if i.get("country_code", False):
                    fi2.write("\'" + i.get("country_code", False) + "\', ")
                else:
                    fi2.write("null, ")


                if i.get("capital", False):
                    fi2.write("\'" + i.get("capital", False) + "\', ")
                else:
                    fi2.write("null, ")


                if i.get("population", False):
                    fi2.write("" + str(i.get("population", False)) + ", ")
                else:
                    fi2.write("0, ")


                if i.get("flag_file_url", False):
                    fi2.write("\'" + i.get("flag_file_url", False) + "\');\n")
                else:
                    fi2.write("null);\n")






if __name__ == "__main__":
    json_to_sql_from_file("raw_data.lst", "data.sql")
