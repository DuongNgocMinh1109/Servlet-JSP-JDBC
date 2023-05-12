import mysql.connector
from faker import Faker
import random
import time
from datetime import datetime

# Kết nối tới cơ sở dữ liệu MySQL
conn = mysql.connector.connect(
    host="127.0.0.1",
    user="root",
    password="root",
    database="servlet-jsp-jdbc"
)

# Tạo đối tượng Faker
fake = Faker()

# Danh sách các category
categories = ['news', 'view', 'world', 'video', 'business', 'politics', 'sport', 'entertainment', 'science', 'law', 'education', 'health', 'life', 'tourism']

# Tạo dữ liệu cho bảng news
def create_news_data():
    cursor = conn.cursor()

    f = open("insert_tables.sql", "a")
    f.write("\n-- ==============================================INSERT DB FOR TABLES news==============================================")
    for _ in range(50):
        title = fake.sentence()
        thumbnail = fake.image_url()
        shortdescription = fake.paragraph()
        content = fake.paragraphs(nb=random.randint(1, 10))[0]
        categoryid = random.choice(categories)
        createddate = fake.date_time_between(start_date='-1y', end_date='now').strftime('%Y-%m-%d %H:%M:%S')
        modifieddate = fake.date_time_between(start_date=datetime.strptime(createddate, '%Y-%m-%d %H:%M:%S'), end_date='now').strftime('%Y-%m-%d %H:%M:%S')
        createdby = fake.name()
        modifiedby = fake.name()

        query = f"INSERT INTO news(title, thumbnail, shortdescription, content, categoryid, createddate, modifieddate, createdby, modifiedby) VALUES ('{title}', '{thumbnail}', '{shortdescription}', '{content}', (SELECT id FROM category WHERE code='{categoryid}'), '{createddate}', '{modifieddate}', '{createdby}', '{modifiedby}')"
        print('[+] ' + query + '\n')
        f.write('\n' + query + ';')

        cursor.execute(query)

    conn.commit()
    cursor.close()

# Gọi hàm tạo dữ liệu
create_news_data()

# Đóng kết nối tới cơ sở dữ liệu
conn.close()
