CREATE SEQUENCE SEQ_PID START WITH 1; --PAINT �÷������
CREATE SEQUENCE SEQ_PNO START WITH 1; -- PAINT_PHOTO �÷������


  CREATE OR REPLACE FORCE VIEW "SEMI"."V_GALLERY" ("��ǰ��ȣ", "��ǰ�̸�", "��ǰ����", "ī�װ�", "�۰��̸�", "��ǰ�Ұ�", "��ǰ���۳⵵", "�������ȣ", "���ƿ�", "�۰����̵�") AS 
  SELECT P.PAINT_NO ��ǰ��ȣ ,PAINT_NAME ��ǰ�̸�,PAINT_PRICE ��ǰ����,CATEGORY ī�װ�,ARTIST_NAME �۰��̸�,PAINT_INT ��ǰ�Ұ�
            ,PAINT_MDATE ��ǰ���۳⵵, SIZE_NO �������ȣ, LIKES ���ƿ�, M.USER_ID �۰����̵�
    FROM PAINT P
    JOIN MASTERPIECE M ON (P.PAINT_NO = M.PAINT_NO)
    JOIN MEMBER ME ON (M.USER_ID = ME.USER_ID);
    
SELECT * FROM PAINT_PHOTO WHERE FILELEVEL=0 AND PAINT_NO BETWEEN 1 AND 10;
