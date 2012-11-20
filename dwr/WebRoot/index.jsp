<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>Simple Text Generation Demo</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

  <script type='text/javascript' src='./dwr/engine.js'> </script>
  <script type='text/javascript' src='./dwr/util.js'> </script>
  <script type='text/javascript' src='./dwr/interface/Demo.js'> </script>
  
  <script type="text/javascript" src='index.js'> </script>
</head>
<body>
		<p>
			Name:
			<input type="text" id="demoName" />
			<input value="Send" type="button" onclick="update()" />
			<br />
			Reply:
			<span id="demoReply"></span>
		</p>
</body>
</html>