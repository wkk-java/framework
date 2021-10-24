<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8" />
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
<div class="vote">${voteRemark}</div>
<br/>
<br/>
<div>
<#list resultPublishDtoList as resultDto>
        <div class="vote">投票人姓名：${resultDto.name}</div>
        <div class="vote">投票人证件号：${resultDto.idCard}</div>
        <div class="vote">小区：${resultDto.communityName}</div>
        <div class="vote">楼栋：${resultDto.buildingName}</div>
        <div class="vote">单元楼层房号：${resultDto.roomName}</div>
        <div class="vote">投票时间：${resultDto.createDate ? datetime}</div>
        <div class="vote">电子签名：<a href="${resultDto.signPicUrl?if_exists}" >查看</a></div>
        <div class="vote">投票结果：</div>
        <table class="gridtable" style="repeat-header:yes;repeat-footer:yes;">
            <#list resultDto.communityVoteQuestionList as question>
            <thead>
                <tr>
                    <th>${question_index?if_exists+1 } . ${question.questionTitle}</th>
                </tr>
            </thead>
            <tbody>
                <#list question.communityVoteQuestionItems as item>
                    <tr>
                        <td <#if item.selected == true >style="background-color:gray" </#if> >
                            <#if item.selected == true >已选: </#if> ${item.itemName}
                        </td>
                    </tr>
                </#list>
            </tbody>
            </#list>
        </table>
</#list>
    </div>
</body>
</html>