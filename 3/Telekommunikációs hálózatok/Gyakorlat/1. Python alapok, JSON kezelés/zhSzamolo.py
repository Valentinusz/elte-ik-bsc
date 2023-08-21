import json

with open('result.json', 'r') as file:
    data = json.load(file)
    earned = data["haziPont"]["elert"] + data["zhPont"]["elert"]
    maximumScore = data["haziPont"]["max"] + data["mininetPont"]["max"] + data["zhPont"]["max"]

    thresholds = {2: 0.5 * maximumScore, 3: 0.6 * maximumScore, 4: 0.75 * maximumScore, 5: 0.85 * maximumScore}

    for i in thresholds.keys():
        if (thresholds[i] - earned) < data["mininetPont"]["min"]:
            print("%i: %i" % (i, data["mininetPont"]["min"]))
        else:
            if (thresholds[i] - earned) > data["mininetPont"]["max"]:
                print("%i: reménytelen" % i)
            else:
                print("%i: %i" % (i, (thresholds[i]) - earned))