<?php

require 'vendor/autoload.php';

/** @see https://www.php.net/manual/en/language.namespaces.php */
use Faker\Factory as Factory;

$factory = Factory::create();
echo $factory->name();
echo $factory->filePath();
echo $factory->password();
