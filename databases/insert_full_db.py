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


f = open("insert_full_tables.sql", "a")
# Tạo dữ liệu cho bảng role
def create_role_data():
    cursor = conn.cursor()
    
    roles = [
        {'name': 'Admin', 'code': 'admin'},
        {'name': 'Editor', 'code': 'editor'},
        {'name': 'Viewer', 'code': 'viewer'}
    ]

    f.write("\n\n\n-- ==============================================INSERT DB FOR TABLES role==============================================")
    for role in roles:
        name = role['name']
        code = role['code']
        createddate = fake.date_time_between(start_date='-1y', end_date='now').strftime('%Y-%m-%d %H:%M:%S')
        modifieddate = fake.date_time_between(start_date=datetime.strptime(createddate, '%Y-%m-%d %H:%M:%S'), end_date='now').strftime('%Y-%m-%d %H:%M:%S')
        createdby = fake.name()
        modifiedby = fake.name()

        query = f"INSERT INTO role (name, code, createddate, modifieddate, createdby, modifiedby) VALUES ('{name}', '{code}', '{createddate}', '{modifieddate}', '{createdby}', '{modifiedby}')"
        f.write('\n' + query + ';')
        cursor.execute(query)

    conn.commit()
    cursor.close()

# Tạo dữ liệu cho bảng user
def create_user_data():
    cursor = conn.cursor()

    cursor.execute("SELECT id FROM role")
    role_ids = cursor.fetchall()

    f.write("\n-- ==============================================INSERT DB FOR TABLES user==============================================")
    for _ in range(10):
        username = fake.user_name()
        password = '123456'
        fullname = fake.name()
        status = random.choice([0, 1])
        roleid = random.choice(role_ids)[0]
        createddate = fake.date_time_between(start_date='-1y', end_date='now').strftime('%Y-%m-%d %H:%M:%S')
        modifieddate = fake.date_time_between(start_date=datetime.strptime(createddate, '%Y-%m-%d %H:%M:%S'), end_date='now').strftime('%Y-%m-%d %H:%M:%S')
        createdby = fake.name()
        modifiedby = fake.name()

        query = f"INSERT INTO user (username, password, fullname, status, roleid, createddate, modifieddate, createdby, modifiedby) VALUES ('{username}', '{password}', '{fullname}', {status}, {roleid}, '{createddate}', '{modifieddate}', '{createdby}', '{modifiedby}')"
        f.write('\n' + query + ';')
        cursor.execute(query)

    conn.commit()
    cursor.close()

# Tạo dữ liệu cho bảng category (tiếp tục)
def create_category_data():
    cursor = conn.cursor()

    categories = [
        {'name': 'News', 'code': 'news'},
        {'name': 'View', 'code': 'view'},
        {'name': 'World', 'code': 'world'},
        {'name': 'Video', 'code': 'video'},
        {'name': 'Business', 'code': 'business'},
        {'name': 'Politics', 'code': 'politics'},
        {'name': 'Sport', 'code': 'sport'},
        {'name': 'Entertainment', 'code': 'entertainment'},
        {'name': 'Science', 'code': 'science'},
        {'name': 'Law', 'code': 'law'},
        {'name': 'Education', 'code': 'education'},
        {'name': 'Health', 'code': 'health'},
        {'name': 'Life', 'code': 'life'},
        {'name': 'Tourism', 'code': 'tourism'}
    ]

    f.write("\n-- ==============================================INSERT DB FOR TABLES category==============================================")
    for category in categories:
        name = category['name']
        code = category['code']
        createddate = fake.date_time_between(start_date='-1y', end_date='now').strftime('%Y-%m-%d %H:%M:%S')
        modifieddate = fake.date_time_between(start_date=datetime.strptime(createddate, '%Y-%m-%d %H:%M:%S'), end_date='now').strftime('%Y-%m-%d %H:%M:%S')
        createdby = fake.name()
        modifiedby = fake.name()

        query = f"INSERT INTO category (name, code, createddate, modifieddate, createdby, modifiedby) VALUES ('{name}', '{code}', '{createddate}', '{modifieddate}', '{createdby}', '{modifiedby}')"
        f.write('\n' + query + ';')
        cursor.execute(query)

    conn.commit()
    cursor.close()

# Tạo dữ liệu cho bảng news
def create_news_data():
    cursor = conn.cursor()

    cursor.execute("SELECT id FROM category")
    category_ids = cursor.fetchall()

    f.write("\n-- ==============================================INSERT DB FOR TABLES news==============================================")
    for _ in range(50):
        title = fake.sentence()
        thumbnail = fake.image_url()
        shortdescription = fake.paragraph()
        content = fake.paragraphs(nb=random.randint(1, 10))[0]
        categoryid = random.choice(category_ids)[0]
        createddate = fake.date_time_between(start_date='-1y', end_date='now').strftime('%Y-%m-%d %H:%M:%S')
        modifieddate = fake.date_time_between(start_date=datetime.strptime(createddate, '%Y-%m-%d %H:%M:%S'), end_date='now').strftime('%Y-%m-%d %H:%M:%S')
        createdby = fake.name()
        modifiedby = fake.name()

        query = f"INSERT INTO news (title, thumbnail, shortdescription, content, categoryid, createddate, modifieddate, createdby, modifiedby) VALUES ('{title}', '{thumbnail}', '{shortdescription}', '{content}', {categoryid}, '{createddate}', '{modifieddate}', '{createdby}', '{modifiedby}')"
        f.write('\n' + query + ';')
        cursor.execute(query)

    conn.commit()
    cursor.close()

# # Tạo dữ liệu cho bảng comment
# def create_comment_data():
#     cursor = conn.cursor()

#     cursor.execute("SELECT id FROM user")
#     user_ids = cursor.fetchall()

#     cursor.execute("SELECT id FROM news")
#     news_ids = cursor.fetchall()

#     f.write("\n-- ==============================================INSERT DB FOR TABLES comment==============================================")
#     for _ in range(50):
#         content = fake.par

if __name__ == '__main__':
    # Tạo dữ liệu cho từng bảng
    create_role_data()
    create_user_data()
    create_category_data()
    create_news_data()
    # create_comment_data()
    print("Done!")