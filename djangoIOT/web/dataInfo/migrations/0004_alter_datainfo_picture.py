# Generated by Django 3.2.13 on 2022-06-03 02:43

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('dataInfo', '0003_auto_20220603_1029'),
    ]

    operations = [
        migrations.AlterField(
            model_name='datainfo',
            name='picture',
            field=models.IntegerField(blank=True, default=1, null=True),
        ),
    ]
