<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>投票模板</title>
    <style>
        .title {
            text-align: center;
        }
        .vote {
            margin: 10px;
        }
        table.gridtable {
            font-family: verdana, arial, sans-serif;
            font-size: 11px;
            color: #333333;
            border-width: 1px;
            border-color: #666666;
            border-collapse: collapse;
        }
        table.gridtable th {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #666666;
            background-color: #dedede;
        }
        table.gridtable td {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #666666;
            background-color: #ffffff;
        }
    </style>
</head>
<body>
<div class="title">${voteTitle}</div>
<div>
    <div class="vote">${voteRemark}</div>
    <div class="vote">投票时间：${voteStartTime ? datetime} 至 ${ voteEndTime ? datetime}</div>
    <div class="vote">投票人数：${voteCountNum!}</div>
    <div class="vote">未投票人数：${unvoteCountNum!}</div>
    <div class="vote">投票结果：</div>
    <table class="gridtable">
        <tr>
            <th>投票题目</th>
            <th>结果</th>
        </tr>
        <#list communityVoteQuestionList as question>
        <tr>
            <td>${question.questionTitle}</td>
            <td>${question.questionItemNameStr}</td>
        </tr>
        </#list>
    </table>
</div>
</body>
</html>
