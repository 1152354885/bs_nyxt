# Generated by Django 4.0.4 on 2023-04-10 11:41

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('dataInfo', '0017_datainfo_time'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='rfidinfo',
            name='times',
        ),
        migrations.RemoveField(
            model_name='rfidinfo',
            name='warn',
        ),
        migrations.AddField(
            model_name='rfidinfo',
            name='co2',
            field=models.CharField(default=3, max_length=64),
        ),
    ]
