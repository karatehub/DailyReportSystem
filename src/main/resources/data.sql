INSERT INTO employee(name, created_at, updated_at, delete_flag) VALUES ("煌木　太郎", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);
INSERT INTO employee(name, created_at, updated_at, delete_flag) VALUES ("田中　太郎", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);
INSERT INTO authentication(code, password, role, employee_id) VALUES ("ktaro", "ktaro", "管理者", 1);
INSERT INTO authentication(code, password, role, employee_id) VALUES ("ttaro", "ttaro", "一般", 2);