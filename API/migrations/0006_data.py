# -*- coding: utf-8 -*-
# Generated by Django 1.9.5 on 2016-06-12 20:02
from __future__ import unicode_literals

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('API', '0005_messages'),
    ]

    operations = [
        migrations.CreateModel(
            name='Data',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('start_lat', models.CharField(max_length=20)),
                ('start_long', models.CharField(max_length=20)),
                ('end_lat', models.CharField(max_length=20)),
                ('end_long', models.CharField(max_length=20)),
                ('time', models.CharField(max_length=100)),
                ('user', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='API.User')),
            ],
        ),
    ]