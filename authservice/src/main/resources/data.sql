
INSERT INTO t_role ( NAME, NAMEZH) VALUES ( 'admin', 'admin_zh');
INSERT INTO t_role ( NAME, NAMEZH) VALUES ( 'user', 'user_zh');
INSERT INTO t_user ( NAME, ENABLED, TELEPHONE, PASSWORD, EMAIL, CREATE_TIME, UPDATE_TIME) VALUES ( 'admin', true, '130321321', '123', '123', '2020-10-15 12:18:48.294072', '2020-10-15 12:18:48.294072');

INSERT INTO t_r_u (R_ID, U_ID, COMMENT) VALUES (2, 1, 'admin-user');
INSERT INTO t_r_u (R_ID, U_ID, COMMENT) VALUES (1, 1, 'admin-admin');