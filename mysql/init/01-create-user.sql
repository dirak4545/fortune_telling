-- 모든 호스트에서 접속 가능한 사용자 생성
CREATE USER IF NOT EXISTS 'fortune_user'@'%' IDENTIFIED BY 'fortune_password';
GRANT ALL PRIVILEGES ON fortune_telling.* TO 'fortune_user'@'%';
FLUSH PRIVILEGES;

-- 이미 생성된 사용자 권한 업데이트 (이미 존재하는 경우)
GRANT ALL PRIVILEGES ON fortune_telling.* TO 'fortune_user'@'%';
FLUSH PRIVILEGES;