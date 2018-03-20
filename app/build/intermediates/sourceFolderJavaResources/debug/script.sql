-- Profiles
INSERT INTO PROFILE(profile_name,ring_volume,airplane_mode, vibration) VALUES ('general',50,0,0);
INSERT INTO PROFILE(profile_name,ring_volume,airplane_mode, vibration) VALUES ('Na rua',100,0,0);
INSERT INTO PROFILE(profile_name,ring_volume,airplane_mode, vibration) VALUES ('Corrida de rua',100,0,0);
INSERT INTO PROFILE(profile_name,ring_volume,airplane_mode, vibration) VALUES ('Casa',100,0,0);
INSERT INTO PROFILE(profile_name,ring_volume,airplane_mode, vibration) VALUES ('Dirigindo',50,0,0);
INSERT INTO PROFILE(profile_name,ring_volume,airplane_mode, vibration) VALUES ('DirigindoRapido',10,0,0);
INSERT INTO PROFILE(profile_name,ring_volume,airplane_mode, vibration) VALUES ('Escritorio',0,0,1);
INSERT INTO PROFILE(profile_name,ring_volume,airplane_mode, vibration) VALUES ('Reuniao',0,1,0);
INSERT INTO PROFILE(profile_name,ring_volume,airplane_mode, vibration) VALUES ('Sincronizacao',50,0,0);
--
-- Record constant (Bluetooth)
--
INSERT INTO CONTEXT_CONSTANT(constant_type,constant_name,constant_value) VALUES (4,'bt_escritorio','DC:53:60:4F:02:AA');
INSERT INTO CONTEXT_CONSTANT(constant_type,constant_name,constant_value) VALUES (4,'bt_casa','1C:39:47:0D:4A:BC');
INSERT INTO CONTEXT_CONSTANT(constant_type,constant_name,constant_value) VALUES (4,'bt_carro','00:FF:CD:6A:12:6B');
--
-- Record constant (GPS)
--
INSERT INTO CONTEXT_CONSTANT(constant_type,constant_name,constant_value) VALUES (2,'Escritorio','-21.97969,-47.880300');
INSERT INTO CONTEXT_CONSTANT(constant_type,constant_name,constant_value) VALUES (2,'Casa','-20.999935,-48.210638');
--
-- Rules
--
INSERT INTO RULE(rule_name,current_state_id,new_state_id, priority,action_id,enabled) VALUES ('AtivarRua',1,2,5,2,1);
INSERT INTO RULE(rule_name,current_state_id,new_state_id, priority,action_id,enabled) VALUES ('DesativarRua_GPSNaoValido',2,1,5,1,1);
INSERT INTO RULE(rule_name,current_state_id,new_state_id, priority,action_id,enabled) VALUES ('DesativarRua_LocalCasa',2,1,5,1,1);
INSERT INTO RULE(rule_name,current_state_id,new_state_id, priority,action_id,enabled) VALUES ('DesativarRua_LocalEscritorio',2,1,5,1,1);
INSERT INTO RULE(rule_name,current_state_id,new_state_id, priority,action_id,enabled) VALUES ('AtivarCorrida',2,3,5,3,1);
INSERT INTO RULE(rule_name,current_state_id,new_state_id, priority,action_id,enabled) VALUES ('DesativarCorrida_GPSNaoValido',3,2,5,2,1);
INSERT INTO RULE(rule_name,current_state_id,new_state_id, priority,action_id,enabled) VALUES ('DesativarCorrida_VelocidadeMenorIgual5',3,2,5,2,1);
INSERT INTO RULE(rule_name,current_state_id,new_state_id, priority,action_id,enabled) VALUES ('AtivarDirigindo_Geral',1,5,5,5,1);
INSERT INTO RULE(rule_name,current_state_id,new_state_id, priority,action_id,enabled) VALUES ('AtivarDirigindo_Casa',4,5,1,5,1);
INSERT INTO RULE(rule_name,current_state_id,new_state_id, priority,action_id,enabled) VALUES ('AtivarDirigindo_Escritorio',7,5,1,5,1);
INSERT INTO RULE(rule_name,current_state_id,new_state_id, priority,action_id,enabled) VALUES ('AtivarDirigindo_NaRua',2,5,1,5,1);
INSERT INTO RULE(rule_name,current_state_id,new_state_id, priority,action_id,enabled) VALUES ('DesativarDirigindo',5,1,1,1,1);
INSERT INTO RULE(rule_name,current_state_id,new_state_id, priority,action_id,enabled) VALUES ('AtivarDirigindoRapido',5,6,5,6,1);
INSERT INTO RULE(rule_name,current_state_id,new_state_id, priority,action_id,enabled) VALUES ('DesativarDirigindoRapido_GPSNaoValido',6,5,5,5,1);
INSERT INTO RULE(rule_name,current_state_id,new_state_id, priority,action_id,enabled) VALUES ('DesativarDirigindoRapido_VelocidadeMenorIgual70',6,5,5,5,1);
INSERT INTO RULE(rule_name,current_state_id,new_state_id, priority,action_id,enabled) VALUES ('AtivarCasa_Bluetooth',1,4,5,4,1);
INSERT INTO RULE(rule_name,current_state_id,new_state_id, priority,action_id,enabled) VALUES ('AtivarCasa_GPS',1,4,4,4,1);
INSERT INTO RULE(rule_name,current_state_id,new_state_id, priority,action_id,enabled) VALUES ('DesativarCasa_Bluetooth',4,1,5,1,1);
INSERT INTO RULE(rule_name,current_state_id,new_state_id, priority,action_id,enabled) VALUES ('DesativarCasa_GPS',4,1,5,1,1);
INSERT INTO RULE(rule_name,current_state_id,new_state_id, priority,action_id,enabled) VALUES ('AtivarEscritorio_Bluetooh',1,7,5,7,1);
INSERT INTO RULE(rule_name,current_state_id,new_state_id, priority,action_id,enabled) VALUES ('DesativarEscritorio_Bluetooth',7,1,5,1,1);
INSERT INTO RULE(rule_name,current_state_id,new_state_id, priority,action_id,enabled) VALUES ('DesativarEscritorio_GPS',7,1,5,1,1);
INSERT INTO RULE(rule_name,current_state_id,new_state_id, priority,action_id,enabled) VALUES ('AtivarReuniao',7,8,4,8,1);
INSERT INTO RULE(rule_name,current_state_id,new_state_id, priority,action_id,enabled) VALUES ('DesativarReuniao',8,7,4,7,1);
INSERT INTO RULE(rule_name,current_state_id,new_state_id, priority,action_id,enabled) VALUES ('AtivarSincronizacao_BTCasa',1,9,9,9,1);
INSERT INTO RULE(rule_name,current_state_id,new_state_id, priority,action_id,enabled) VALUES ('AtivarSincronizacao_BT_Escritorio',1,7,9,7,1);
INSERT INTO RULE(rule_name,current_state_id,new_state_id, priority,action_id,enabled) VALUES ('DesativarSincronizacao',9,1,9,1,1);
--
-- Filter
--
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (1,1,true,1);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (2,2,'-20.999935,-48.210638',1);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (2,2,'-20.999935,-48.210638',1);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (1,2,true,2);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (2,1,'-20.999935,-48.210638',3);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (2,1,'-20.999935,-48.210638',4);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (2,1,'-21.979769,-47.880300',5);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (3,3,5,5);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (1,2,true,6);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (3,6,5,0);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (3,6,5,7);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (4,1,'00:FF:CD:6A:12:6B',8);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (4,1,'00:FF:CD:6A:12:6B',9);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (4,1,'00:FF:CD:6A:12:6B',10);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (4,1,'00:FF:CD:6A:12:6B',11);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (4,2,'00:FF:CD:6A:12:6B',12);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (1,1,true,13);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (3,3,70,13);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (1,2,true,14);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (3,6,70,15);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (4,1,'1C:39:47:0D:4A:BC',16);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (1,1,true,17);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (2,1,'-20.999935,-48.210638',17);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (4,2,'1C:39:47:0D:4A:BC',18);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (1,1,true,19);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (2,2,'-20.999935,-48.210638',20);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (4,1,'1C:39:47:0D:4A:BC',21);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (4,2,'DC:53:60:4F:02:AA',22);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (2,1,'-21.979769,-47.880300',22);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (2,2,'-21.979769,-47.880300',22);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (6,4,'14:00:00',23);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (5,4,3,23);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (6,4,'16:00:00',24);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (4,1,'1C:39:47:0D:4A:BC',25);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (4,1,'DC:53:60:4F:02:AA',26);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (4,2,'1C:39:47:0D:4A:BC',27);
INSERT INTO FILTER(cv_type,cv_operator,value,rule_row_id) VALUES (4,2,'1C:39:47:0D:4A:BC',27);
