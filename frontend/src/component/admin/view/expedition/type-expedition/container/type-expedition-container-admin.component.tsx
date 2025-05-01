import React from 'react';
import {KeyboardAvoidingView, Platform, SafeAreaView} from 'react-native';
import {globalStyle} from '../../../../../../shared/globalStyle';

import TypeExpeditionAdminTabNavigation from '../../../../../../navigation/admin/expedition/TypeExpeditionAdminTabNavigation';

const TypeExpeditionAdmin = () => {
  return (
    <SafeAreaView style={globalStyle.myContainer}>

      <KeyboardAvoidingView
        behavior={Platform.OS === 'ios' ? 'padding' : 'height'}
        style={{ flex: 1 }} >
        <TypeExpeditionAdminTabNavigation />
      </KeyboardAvoidingView>
    </SafeAreaView>
  );
};

export default TypeExpeditionAdmin;
