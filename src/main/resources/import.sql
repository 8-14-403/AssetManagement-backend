-- 预设数据

-- 预设用户

INSERT INTO users (user_id, email, password, phone, username) VALUES ('00010001-0001-0001-0001-000100010001', 'htjn@ht.com', '$2a$10$IlH1Osss54bWF/fu8SzvH.YHUfqim/0agawoxnNJhr9eSTqygF4Ji', '01234567890', 'admin');


-- 预设权限

INSERT INTO authority (id, name) VALUES ('00010001-0001-0001-0002-000100020001', 'ROLE_USER');
INSERT INTO authority (id, name) VALUES ('00010001-0001-0001-0002-000100020002', 'ROLE_ADMIN');


-- 绑定用户-权限
INSERT INTO user_authority (user_id, authority_id) VALUES ('00010001-0001-0001-0001-000100010001', '00010001-0001-0001-0002-000100020001');
INSERT INTO user_authority (user_id, authority_id) VALUES ('00010001-0001-0001-0001-000100010001', '00010001-0001-0001-0002-000100020002');